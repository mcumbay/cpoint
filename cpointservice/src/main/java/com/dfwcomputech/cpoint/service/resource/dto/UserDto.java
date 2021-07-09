package com.dfwcomputech.cpoint.service.resource.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private String username;
	private String password;
	private LocalDateTime created;	
	private LocalDateTime updated;
	
	public UserDto(String userName, String password) {
		this.username=userName;
		this.password=password;
	}
}
