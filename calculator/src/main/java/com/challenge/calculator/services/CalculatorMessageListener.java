package com.challenge.calculator.services;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.challenge.calculator.dtos.CalcRequest;
import com.challenge.calculator.dtos.CalcResult;
import com.google.gson.Gson;

@Slf4j
@Service
public class CalculatorMessageListener {

	private static final Gson gson = new Gson();
	private final CalculatorService calculatorService;

	public CalculatorMessageListener(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@RabbitListener(queues = "${calculator.queues}")
	public byte[] process(String message) {

		log.info( "processing message: {}", message );
		return Optional.ofNullable( parseMessage( message ) )
				.map( calculatorService::calculate )
				.map( this::buildResultResponse )
				.orElseThrow();
	}

	private CalcRequest parseMessage(String message) {
		return gson.fromJson( message, CalcRequest.class );
	}

	private byte[] buildResultResponse(BigDecimal calcResult) {
		return gson.toJson( CalcResult.builder()
						.result( calcResult )
						.build() )
				.getBytes();
	}
}
