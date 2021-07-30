package com.dfwcomputech.cpoint.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserServiceComponentTest {
	@Autowired
	IUserService userService;
	
	@Test
	void shouldReturnCompleteUser() throws CPointException {
		//Arrange
		User user = new User("Samuel", "1234");
		//Act
		User completeUser = userService.createUser(user);
		log.info("{}",completeUser);
		//Assert
		assertNotNull(completeUser.getCreated());
	}
	
	@Test
	void shouldThrowExceptionIfUserAlreadyExist() throws CPointException{
		//Arrange
		User user1 = new User("Samuel", "1234");
		userService.createUser(user1);
		User user2 = new User("Samuel", "6789");
		//Act-Assert
		assertThrows(CPointException.class,()->userService.createUser(user2) );
		
	}

}

