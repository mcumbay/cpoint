package com.dfwcomputech.cpoint.service.acceptance.createchore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.dfwcomputech.cpoint.integration.repository.ChoreRepository;
import com.dfwcomputech.cpoint.service.resource.dto.ChoreDto;
import com.dfwcomputech.cpoint.service.util.HttpClient;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateChoreStepDef {
	@Autowired
	private ChoreRepository choreRepository;
	
	@Autowired
	private HttpClient<ChoreDto> choreHttpClient;
	
	private HttpStatus status;
	private String error;
		
	@When("I create a new chore with name {string}, description {string} and points {int}")
	public void i_create_a_new_chore_with_name_description_and_points(String name, String description, Integer points) {
		ChoreDto chore = new ChoreDto(name,description,points);
		try {
			status = choreHttpClient.put(chore);
		} catch (HttpClientErrorException e) {
			status=e.getStatusCode();
			error=e.getMessage();
		}
		log.debug("Status code = {}",status);	    	   
	}
	
	@When("I create a new chore with name {string}, description {string} and points ")
	public void i_create_a_new_chore_with_name_description_and_points(String name, String description) {
		i_create_a_new_chore_with_name_description_and_points(name,description,null);
	}

	@Then("I should be able to find the chore with name {string} on the application")
	public void i_should_be_able_to_find_the_chore_with_name_on_the_application(String name) {
	    assertThat(status).isEqualTo(HttpStatus.CREATED);
	    assertTrue(!choreRepository.findByName(name).isEmpty());
	}
	
	@Then("An error message {string} is shown to the user")
	public void an_error_message_is_shown(String expectedMessage) {
	    assertThat(status).isEqualTo(HttpStatus.PRECONDITION_FAILED);
	    assertTrue(error.contains(expectedMessage));
	}

}
