package com.challenge.rest.resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.rest.dtos.CalcRequestMessage;
import com.challenge.rest.exceptions.UseCaseException;
import com.challenge.rest.services.CalculatorRequestExecutor;

@ExtendWith(MockitoExtension.class)
class CalculatorResourceTest {

	private static final String MATH_OPERATOR_NOT_FOUND = "The math operator informed is not supported!";

	@InjectMocks
	private CalculatorResource calculatorResource;

	@Mock
	private CalculatorRequestExecutor requestExecutor;

	@Test
	void shouldCallRequestExecutor() {
		calculatorResource.calculate( BigDecimal.ONE, BigDecimal.ONE, "sum" );

		verify( requestExecutor, atMostOnce() ).execute( any( CalcRequestMessage.class ) );
	}

	@Test
	void shouldThrowWhenOperatorNotFound() {
		var exception = assertThrows( UseCaseException.class,
				() -> calculatorResource.calculate( BigDecimal.ONE, BigDecimal.ONE, "square" ) );

		verifyNoInteractions( requestExecutor );
		assertThat( exception.getMessage(), equalTo( MATH_OPERATOR_NOT_FOUND ) );
	}

}