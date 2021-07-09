package com.dfwcomputech.cpoint.service.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.service.IUserService;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

@RestController
public class UserController {

	private IUserService userService;	
	private ModelMapper modelMapper;
	   
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper=modelMapper;
	}
	@Autowired
	public UserController(IUserService userService) {
		this.userService=userService;
	}

	@PostMapping("/users")
	public UserDto post(@RequestBody UserDto userDto) throws CPointException {
		User userEntity = userService.createUser(toEntity(userDto));
		return toDto(userEntity);
	}
	
	@GetMapping("/users")
	public String get() {
		return "Hello There";
	}
	
	public UserDto toDto(User user) {
	    return modelMapper.map(user, UserDto.class);	    	   
	}
	
	public User toEntity(UserDto userDto){
	    return modelMapper.map(userDto, User.class);	    	 	  	    
	}
}
