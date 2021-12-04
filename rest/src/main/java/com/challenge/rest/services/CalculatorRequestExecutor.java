package com.challenge.rest.services;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.challenge.rest.dtos.CalcRequestMessage;
import com.challenge.rest.dtos.CalcResponse;
import com.challenge.rest.exceptions.UseCaseException;
import com.google.gson.Gson;

@Slf4j
@Service
public class CalculatorRequestExecutor {

	private static final Gson gson = new Gson();

	private final Queue queue;
	private final RabbitTemplate rabbitTemplate;
	private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong, try again later!";

	public CalculatorRequestExecutor(RabbitTemplate rabbitTemplate, Queue queue) {
		this.rabbitTemplate = rabbitTemplate;
		this.queue = queue;
	}

	public CalcResponse execute(CalcRequestMessage request) {
		log.info( "sending request to calculator service: {}", request );
		return Optional.ofNullable( rabbitTemplate.convertSendAndReceive( queue.getName(), buildMessage( request ) ) )
				.map( this::buildResponse )
				.orElseThrow( () -> new UseCaseException( DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR ) );
	}

	private Message buildMessage(CalcRequestMessage request) {
		return MessageBuilder
				.withBody( gson.toJson( request ).getBytes() )
				.setContentType( MessageProperties.CONTENT_TYPE_JSON )
				.build();
	}

	private CalcResponse buildResponse(Object response) {
		return gson.fromJson( String.valueOf( response ), CalcResponse.class );
	}
}
