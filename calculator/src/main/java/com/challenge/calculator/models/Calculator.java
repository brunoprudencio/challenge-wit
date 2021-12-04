package com.challenge.calculator.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Calculator {

	BigDecimal calculate(BigDecimal numberX, BigDecimal numberY);

	class SumOperation implements Calculator {
		@Override public BigDecimal calculate(BigDecimal numberX, BigDecimal numberY) {
			return numberX.add( numberY );
		}
	}

	class SubtractionOperation implements Calculator {
		@Override public BigDecimal calculate(BigDecimal numberX, BigDecimal numberY) {
			return numberX.subtract( numberY );
		}
	}

	class DivideOperation implements Calculator {
		@Override public BigDecimal calculate(BigDecimal numberX, BigDecimal numberY) {
			return numberX.divide( numberY, RoundingMode.HALF_EVEN );
		}
	}

	class MultiplicationOperation implements Calculator {
		@Override public BigDecimal calculate(BigDecimal numberX, BigDecimal numberY) {
			return numberX.multiply( numberY );
		}
	}

	class NotSupportedOperation implements Calculator {
		@Override public BigDecimal calculate(BigDecimal numberX, BigDecimal numberY) {
			return BigDecimal.ZERO;
		}
	}
}
