package com.challenge.rest.models;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.challenge.rest.exceptions.UseCaseException;

public enum MathOperators {

	SUM,
	SUBTRACTION,
	MULTIPLICATION,
	DIVISION;

	public static MathOperators getOperation(String operator) {
		return Arrays.stream( MathOperators.values() )
				.filter( op -> op.name().equalsIgnoreCase( operator ) )
				.findFirst()
				.orElseThrow( () -> new UseCaseException( "The math operator informed is not supported!",
						HttpStatus.BAD_REQUEST ) );
	}
}
