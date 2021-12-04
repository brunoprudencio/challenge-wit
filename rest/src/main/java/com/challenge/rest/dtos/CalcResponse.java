package com.challenge.rest.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalcResponse {

	private BigDecimal result;

}
