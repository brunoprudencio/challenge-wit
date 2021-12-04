package com.challenge.calculator.services;

import com.challenge.calculator.models.MathOperators;

public class MathOperationFactory {

	private static final Calculator.SumOperation sumOperation = new Calculator.SumOperation();
	private static final Calculator.SubtractionOperation subOperation = new Calculator.SubtractionOperation();
	private static final Calculator.DivideOperation divOperation = new Calculator.DivideOperation();
	private static final Calculator.MultiplicationOperation mulOperation = new Calculator.MultiplicationOperation();
	private static final Calculator.NotSupportedOperation notSupportedOperation = new Calculator.NotSupportedOperation();

	public static Calculator getOperationInstance(MathOperators operator) {
		switch (operator) {
		case SUM:
			return sumOperation;
		case SUBTRACTION:
			return subOperation;
		case DIVISION:
			return divOperation;
		case MULTIPLICATION:
			return mulOperation;
		default:
			return notSupportedOperation;
		}
	}
}
