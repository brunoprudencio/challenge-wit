package com.challenge.rest.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

import com.challenge.rest.models.MathOperators;

@Getter
@Builder
public class CalcRequestMessage {

	private final BigDecimal numberX;
	private final BigDecimal numberY;
	private final MathOperators operator;

	@Override
	public String toString() {
		return "CalcRequestMessage{" +
				"numberX=" + numberX +
				", numberY=" + numberY +
				", operator=" + operator +
				'}';
	}
}
