package com.challenge.calculator.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.calculator.dtos.CalcRequest;

@ExtendWith(MockitoExtension.class)
class CalculatorMessageListenerTest {

	@InjectMocks
	private CalculatorMessageListener messageListener;

	@Mock
	private CalculatorService calculatorService;

	@Test
	void shouldProcessMessage() {
		var message = "{\"numberX\":1,\"numberY\":1,\"operator\":\"SUM\"}";
		when( calculatorService.calculate( any( CalcRequest.class ) ) ).thenReturn( BigDecimal.valueOf( 2 ) );

		messageListener.process( message );

		verify( calculatorService, atMostOnce() ).calculate( any( CalcRequest.class ) );
	}

}