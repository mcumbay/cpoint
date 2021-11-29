package com.dfwcomputech.cpoint.java;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Java14Tests {
	private static final int CASH=1;
	private static final int RFCR=2;
	private static final int PSEUDO=3;
	private static final int UNKNOWN =-1;
	
	/*
	 * JEP-358 Helpful NullPointerExceptions
	 */
	@Test
	void jep358() {
		// Arrange
		String input = null;
		// Act && Assert
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> input.toUpperCase(Locale.US));

	}
	
	/*
	 * JEP 361: Switch Expressions (Standard)
	 */
	void jep361() {
		//Arrange
		String typeCheck = "REVERSE";
		//Act & Assert
		assertEquals(UNKNOWN, getCheckType(typeCheck));
	}
	
	private static int getCheckType(String typeStr) {
        return switch (typeStr) {
            case "cash", "Cash" -> CASH;
            case "rfcr" -> RFCR;
            case "pseudo", "Pseudo", "PSEUDO" -> {
                log.debug("Supports multi line block!");
                yield PSEUDO;
            }
            default -> UNKNOWN;
        };
    }
}