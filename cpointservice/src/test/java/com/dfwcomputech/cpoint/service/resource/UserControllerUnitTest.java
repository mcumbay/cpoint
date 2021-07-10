package com.dfwcomputech.cpoint.service.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.model.UserType;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

@DisplayName("Unit Test: UserController")
@Tag("UnitTest")
class UserControllerUnitTest {

	private UserController getUserController() {
		UserController userController = new UserController(null);
		userController.setModelMapper(new ModelMapper());
		return userController;
	}
	
	private UserDto getMockUserDto(boolean admin) {
		return new UserDto("user01", "pass01", admin);
	}

	@Test
	@DisplayName("Should Map not Admin UserDto to User")
	void shouldMapNotAdminUserDtoToUser() {
		//Arrange
		UserDto userDto = getMockUserDto(false);
		//Action
		User userEntity = getUserController().toEntity(userDto);
		//Assert
		assertEquals(userDto.getUsername(), userEntity.getUserName());
		assertEquals(userDto.getPassword(), userEntity.getPassword());
		assertEquals(UserType.KID, userEntity.getType());
	}
	
	@Test
	@DisplayName("Should Map Admin UserDto to User")
	void shouldMapAdminUserDtoToUser() {
		//Arrange
		UserDto userDto = getMockUserDto(true);
		//Action
		User userEntity = getUserController().toEntity(userDto);
		//Assert
		assertEquals(userDto.getUsername(), userEntity.getUserName());
		assertEquals(userDto.getPassword(), userEntity.getPassword());
		assertEquals(UserType.PARENT, userEntity.getType());
	}
	
}
