package com.dfwcomputech.cpoint.service.acceptance;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;

@Component
@ScenarioScope
public class CurrentTestInfo {
	// Current url under test
	@Getter@Setter
	private String completeEndPoint;
	
	//Current http status
	@Getter@Setter
	private HttpStatus status;
	
	//Current error message
	@Getter@Setter
	private String error;
		
}
