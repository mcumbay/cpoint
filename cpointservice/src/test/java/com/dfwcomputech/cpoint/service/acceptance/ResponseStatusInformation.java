package com.dfwcomputech.cpoint.service.acceptance;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;

@Component
@ScenarioScope
public class ResponseStatusInformation {
	
	@Getter@Setter
	private HttpStatus status;
	@Getter@Setter
	private String error;

}
