package com.dfwcomputech.cpoint.service.resource.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private String username;
	private String password;
	private String type;
	private LocalDateTime created;	
	private LocalDateTime updated;
	
	public UserDto(String userName, String password) {
		this.username=userName;
		this.password=password;
		this.type="KID";
	}
	
	public UserDto(String userName, String password,boolean isAdmin) {
		this(userName,password);
		this.type= (isAdmin?"PARENT":"KID");
	}
}
