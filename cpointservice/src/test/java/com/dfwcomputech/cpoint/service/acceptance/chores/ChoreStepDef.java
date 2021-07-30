package com.dfwcomputech.cpoint.service.acceptance.chores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.dfwcomputech.cpoint.integration.repository.ChoreRepository;
import com.dfwcomputech.cpoint.service.acceptance.ResponseStatusInformation;
import com.dfwcomputech.cpoint.service.resource.dto.ChoreDto;
import com.dfwcomputech.cpoint.service.util.HttpClient;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ChoreStepDef {
	@Autowired
	private ChoreRepository choreRepository;
	
	@Autowired
	private HttpClient<ChoreDto> choreHttpClient;
	
	@Autowired
	private ResponseStatusInformation statusInfo;
		
	@When("I create a new chore with name {string}, description {string} and points {int}")
	public void i_create_a_new_chore_with_name_description_and_points(String name, String description, Integer points) {
		ChoreDto chore = new ChoreDto(name,description,points);
		HttpStatus status=null;
		try {
			status = choreHttpClient.put(chore);
		} catch (HttpClientErrorException e) {
			status=e.getStatusCode();
			statusInfo.setError(e.getMessage());
		}
		statusInfo.setStatus(status);
	}
	
	@When("I create a new chore with name {string}, description {string} and points ")
	public void i_create_a_new_chore_with_name_description_and_points(String name, String description) {
		i_create_a_new_chore_with_name_description_and_points(name,description,null);
	}
	
	@Then("I should be able to find the chore with name {string} on the application")
	public void i_should_be_able_to_find_the_chore_with_name_on_the_application(String name) {
	    assertThat(statusInfo.getStatus()).isEqualTo(HttpStatus.CREATED);
	    assertTrue(!choreRepository.findByName(name).isEmpty());
	}
	
}
