package com.dfwcomputech.cpoint.service.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

class UserControllerUnitTest {

	private UserController getUserController() {
		UserController userController = new UserController(null);
		userController.setModelMapper(new ModelMapper());
		return userController;
	}

	@Test
	void shouldMapUserDtoToUserEntity() {
		//Arrange
		UserDto userDto = new UserDto("user01", "pass01");
		//Action
		User userEntity = getUserController().toEntity(userDto);
		//Assert
		assertEquals(userDto.getUsername(), userEntity.getUserName());
		assertEquals(userDto.getPassword(), userEntity.getPassword());
	}
	
}
