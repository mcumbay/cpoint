package com.dfwcomputech.cpoint.integration.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message="User Name cannot be null")
	private String userName;
	
	@NotBlank(message="Password cannot be null")
	private String password;
	
	@NotNull(message = "Type cannot be null")
	private UserType type;
	
	private LocalDateTime created;
		
	private LocalDateTime updated;
	
	public User(String userName,String password) {
		this.userName=userName;
		this.password=password;
		this.type = UserType.KID;
		this.created = LocalDateTime.now();
		this.updated = this.created;
	}
	
	public User(String userName, String password,UserType type) {
		this(userName,password);
		this.type=type;
	}
	
}
