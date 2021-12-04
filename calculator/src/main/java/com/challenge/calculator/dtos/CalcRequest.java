package com.challenge.calculator.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

import com.challenge.calculator.models.MathOperators;

@Getter
@Builder
public class CalcRequest {

	private BigDecimal numberX;
	private BigDecimal numberY;
	private MathOperators operator;

}
