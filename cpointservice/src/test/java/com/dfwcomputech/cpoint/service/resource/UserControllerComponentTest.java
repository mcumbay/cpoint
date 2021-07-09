package com.dfwcomputech.cpoint.service.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerComponentTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void userCreationWorksThroughAllLayers() throws Exception{
		//Arrange
		UserDto user = new UserDto("test01", "mypassword");
		//Action
	    mockMvc.perform(post("/users")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(user)))
	            .andExpect(status().isOk());
	    //Assert
	    User userEntity = userRepository.findFirstByUserName("test01");
	    assertThat(userEntity.getPassword()).isEqualTo("mypassword");
	}

}
