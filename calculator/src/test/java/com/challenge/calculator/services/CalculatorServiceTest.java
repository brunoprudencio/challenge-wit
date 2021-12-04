package com.challenge.calculator.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.calculator.dtos.CalcRequest;
import com.challenge.calculator.models.MathOperators;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

	@InjectMocks
	private CalculatorService calculatorService;

	@Test
	void shouldCalculateSum() {
		var operation = MathOperators.SUM;
		var request = buildRequest( BigDecimal.valueOf( 9.5139 ), BigDecimal.valueOf( 7.6882 ), MathOperators.SUM );

		var result = calculatorService.calculate( request );

		assertThat( MathOperationFactory.getOperationInstance( operation ),
				instanceOf( Calculator.SumOperation.class ) );
		assertThat( result, equalTo( BigDecimal.valueOf( 17.2021 ) ) );
	}

	@Test
	void shouldCalculateSubtraction() {
		var operation = MathOperators.SUBTRACTION;
		var request = buildRequest( BigDecimal.valueOf( 9.5139 ), BigDecimal.valueOf( 7.6882 ),
				MathOperators.SUBTRACTION );

		var result = calculatorService.calculate( request );

		assertThat( MathOperationFactory.getOperationInstance( operation ),
				instanceOf( Calculator.SubtractionOperation.class ) );
		assertThat( result, equalTo( BigDecimal.valueOf( 1.8257 ) ) );
	}

	@Test
	void shouldCalculateMultiplication() {
		var operation = MathOperators.MULTIPLICATION;
		var request = buildRequest( BigDecimal.valueOf( 9.5139 ), BigDecimal.valueOf( 7.6882 ),
				MathOperators.MULTIPLICATION );

		var result = calculatorService.calculate( request );

		assertThat( MathOperationFactory.getOperationInstance( operation ),
				instanceOf( Calculator.MultiplicationOperation.class ) );
		assertThat( result, equalTo( BigDecimal.valueOf( 73.14476598 ) ) );
	}

	@Test
	void shouldCalculateDivision() {
		var operation = MathOperators.DIVISION;
		var request = buildRequest( BigDecimal.valueOf( 9.5139 ), BigDecimal.valueOf( 7.6882 ), operation );

		var result = calculatorService.calculate( request );

		assertThat( MathOperationFactory.getOperationInstance( operation ),
				instanceOf( Calculator.DivideOperation.class ) );
		assertThat( result, equalTo( BigDecimal.valueOf( 1.2375 ) ) );
	}

	private CalcRequest buildRequest(BigDecimal numberX, BigDecimal numberY, MathOperators operator) {
		return CalcRequest.builder()
				.numberX( numberX )
				.numberY( numberY )
				.operator( operator )
				.build();
	}

}