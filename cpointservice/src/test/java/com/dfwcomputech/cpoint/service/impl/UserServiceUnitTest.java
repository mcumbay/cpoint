package com.dfwcomputech.cpoint.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.IUserService;

@DisplayName("Unit Test: User Service")
class UserServiceUnitTest {
	
	User existingUser;

	// Mock Object
	private IUserService mockUserService() {
		UserRepository userRepository = mock(UserRepository.class);
		existingUser = new User("userTest01", "userPass");
		when(userRepository.findFirstByUserName(existingUser.getUserName())).thenReturn(existingUser);
		return new UserService(userRepository);
	}

	@ParameterizedTest(name = "Test {index} - User Name = {0} Password ={1}")
	@CsvFileSource(resources = "/createUser_ShouldFail.csv", numLinesToSkip = 1)
	@DisplayName("Create User should fail when User Name or Password are empty. Also if a User Name already Exists")
	void createUser_ShouldFailForEmptyUserNameOrPassword_and_ExistingUserName(String userName, String password) {
		// Arrange
		IUserService userService = mockUserService();
		User newUser = new User(userName, password);
		// Action //Assert
		assertThrows(CPointException.class, () -> userService.createUser(newUser));
	}

}
