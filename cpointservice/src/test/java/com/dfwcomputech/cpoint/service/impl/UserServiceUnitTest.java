package com.dfwcomputech.cpoint.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.IUserService;

class UserServiceUnitTest {

	// Mock Object
	private IUserService mockUserService() {
		UserRepository userRepository = mock(UserRepository.class);
		User existingUser = new User("userTest01", "userPass");
		when(userRepository.findFirstByUserName(existingUser.getUserName())).thenReturn(existingUser);
		return new UserService(userRepository);
	}

	@ParameterizedTest
	@CsvSource(value = { "null,anyPassword", "userTest02,null", "userTest01,anyPassword" }, nullValues = { "null" })
	void createUserShouldValidate(String userName, String password) {
		// Arrange
		IUserService userService = mockUserService();
		User newUser = new User(userName, password);
		// Action //Assert
		assertThrows(CPointException.class, () -> userService.createUser(newUser));
	}

}
