package com.challenge.calculator.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.challenge.calculator.dtos.CalcRequest;

@Service
public class CalculatorService {

	public BigDecimal calculate(CalcRequest request) {
		return MathOperationFactory
				.getOperationInstance( request.getOperator() )
				.calculate( request.getNumberX(), request.getNumberY() );
	}
}
