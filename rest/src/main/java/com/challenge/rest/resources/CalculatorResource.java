package com.challenge.rest.resources;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rest.dtos.CalcRequestMessage;
import com.challenge.rest.dtos.CalcResponse;
import com.challenge.rest.models.MathOperators;
import com.challenge.rest.services.CalculatorRequestExecutor;

@RestController
@RequestMapping("/calculate")
public class CalculatorResource {

	private final CalculatorRequestExecutor requestExecutor;

	public CalculatorResource(CalculatorRequestExecutor messagePublisher) {
		this.requestExecutor = messagePublisher;
	}

	@GetMapping
	public ResponseEntity<CalcResponse> calculate(
			@RequestParam BigDecimal numberX,
			@RequestParam BigDecimal numberY,
			@RequestParam String operator) {

		return ResponseEntity.ok()
				.body( requestExecutor.execute(
						CalcRequestMessage.builder()
								.numberX( numberX )
								.numberY( numberY )
								.operator( MathOperators.getOperation( operator ) )
								.build() ) );
	}
}
