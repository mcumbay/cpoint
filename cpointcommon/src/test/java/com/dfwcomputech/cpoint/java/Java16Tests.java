package com.dfwcomputech.cpoint.java;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Java16Tests {

	/*
	 * JEP-394 Pattern Matching for instanceof
	 */
	int numberOfCorrectCheckNumbers =0;
	
	@Test
	void jep394() {
		//Arrange
		List<Object> checkNumbers = Stream.of(634623, "6364757", 1.5, "CHK0001").collect(Collectors.toList());
		//Act		
		checkNumbers.forEach(checkNumber -> {if(isFormatted(checkNumber))
				numberOfCorrectCheckNumbers++;});
		//Assert
		assertEquals(2,numberOfCorrectCheckNumbers);
	}

	private boolean isFormatted(Object checkNumber) {
		if (checkNumber instanceof String s && s.matches("[0-9]+")) {
			log.debug("Check number was String = {}", s);
			return true;
		} else if (checkNumber instanceof Integer i && i > 0) {
			log.debug("Check number was numeric = {}", i);
			return true;
		} else {
			log.debug("Incorrect Format for Check Number = {}", checkNumber);
			return false;
		}
	}
	
	/*
	 * JEP-395 Records
	 */
	@Test	
	void jep395() {
		//Arrange
		record Point(int x,int y) {}
		Point p1 = new Point(1, 2);
		//Act
		int x = p1.x();
		//Assert
		assertEquals(1,x);
	}

}
