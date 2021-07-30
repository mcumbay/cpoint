package com.dfwcomputech.cpoint.service.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.service.IUserService;
import com.dfwcomputech.cpoint.service.resource.dto.AssignChoreParam;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CPointEndPoints.USERS)
@Slf4j
public class UserController {
	
	private IUserService userService;	
	private ModelMapper mapper;
	   
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.mapper=modelMapper;
	}
	
	@Autowired
	public UserController(IUserService userService) {
		this.userService=userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto post(@RequestBody UserDto userDto){
		try {
			User userEntity = userService.createUser(mapper.map(userDto,User.class));
			return mapper.map(userEntity,UserDto.class);	
		} catch (CPointException e) {
			log.debug("Validation Error: {}",e.getMessage());			
	        throw new ResponseStatusException(
	                 HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
		}
		
	}
	
	@PostMapping("/assign")
	public void assign(@RequestBody AssignChoreParam param) {
		try {
			userService.assignChore(param.getUserName(),param.getChoreName());		
		} catch (CPointException e) {
	        throw new ResponseStatusException(
	                 HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
		}
		
	}
		
}
