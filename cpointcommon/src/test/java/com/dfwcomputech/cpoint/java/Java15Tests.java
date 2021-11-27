package com.dfwcomputech.cpoint.java;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Java15Tests {

	/*
	 * JEP-378 Text Blocks
	 */
	@Test
	void jep378(){
		//Arrange
		String argument = "INDIANAPOLIS";
		String query = """
	               SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
	               WHERE "CITY" = '%s'
	               ORDER BY "EMP_ID", "LAST_NAME";
	               """;
		 //Act
		 String finalQuery = query.formatted(argument);
		 //Assert
		 assertTrue(finalQuery.contains(argument));
		 log.debug(finalQuery); 
	}
	
}
