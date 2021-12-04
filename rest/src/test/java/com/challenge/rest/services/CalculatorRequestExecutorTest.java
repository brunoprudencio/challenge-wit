package com.challenge.rest.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;

import com.challenge.rest.dtos.CalcRequestMessage;
import com.challenge.rest.dtos.CalcResponse;
import com.challenge.rest.exceptions.UseCaseException;
import com.challenge.rest.models.MathOperators;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
class CalculatorRequestExecutorTest {

	@InjectMocks
	private CalculatorRequestExecutor requestExecutor;

	@Mock
	private RabbitTemplate rabbitTemplate;

	@Mock
	private Queue queue;

	private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong, try again later!";
	private static final Gson gson = new Gson();

	@Test
	void shouldExecute() {
		var request = buildRequest( BigDecimal.ONE, BigDecimal.ONE, MathOperators.SUM );
		var queueName = "calculate";
		when( queue.getName() ).thenReturn( queueName );
		when( rabbitTemplate.convertSendAndReceive( queueName, buildMessage( request ) ) ).thenReturn(
				buildResponse() );

		var response = requestExecutor.execute( request );

		verify( rabbitTemplate, atMostOnce() ).convertSendAndReceive( any() );
		assertThat( response.getResult(), equalTo( BigDecimal.valueOf( 2 ) ) );

	}

	@Test
	void shouldThrowWhenFailToExecute() {
		var exception = assertThrows( UseCaseException.class,
				() -> requestExecutor.execute( buildRequest( BigDecimal.ONE, BigDecimal.ONE, MathOperators.SUM ) ) );

		verify( rabbitTemplate, never() ).convertSendAndReceive( any() );
		assertThat( exception.getMessage(), equalTo( DEFAULT_ERROR_MESSAGE ) );
		assertThat( exception.getHttpStatus(), equalTo( HttpStatus.INTERNAL_SERVER_ERROR ) );

	}

	private Message buildMessage(CalcRequestMessage requestMessage) {
		return MessageBuilder
				.withBody( gson.toJson( requestMessage ).getBytes() )
				.setContentType( MessageProperties.CONTENT_TYPE_JSON )
				.build();
	}

	private CalcRequestMessage buildRequest(BigDecimal numberX, BigDecimal numberY, MathOperators operator) {
		return CalcRequestMessage.builder()
				.numberX( numberX )
				.numberY( numberY )
				.operator( operator )
				.build();
	}

	private Object buildResponse() {
		return gson.toJson( CalcResponse.builder()
				.result( BigDecimal.valueOf( 2 ) )
				.build() );
	}
}