package com.dfwcomputech.cpoint.service.acceptance.chores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dfwcomputech.cpoint.integration.model.Chore;
import com.dfwcomputech.cpoint.integration.repository.ChoreRepository;
import com.dfwcomputech.cpoint.service.acceptance.CurrentTestInfo;
import com.dfwcomputech.cpoint.service.resource.dto.ChoreDto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//Chore Domain
public class ChoreStepDef {
	
	@Autowired
	private ChoreRepository choreRepository;
	
	@Autowired
	private CurrentTestInfo testInfo;
		
	private RestTemplate template = new RestTemplate();
	
	@Given("A chore with name {string}, description {string} and points {int} already exists")
	public void a_chore_with_name_description_and_points_already_exists(String name, String description, Integer points) {
		Chore chore = new Chore(name,description,points);
		choreRepository.save(chore);	    
	}
		
	@When("I create a new chore with name {string}, description {string} and points {int}")
	public void i_create_a_new_chore_with_name_description_and_points(String name, String description, Integer points) {
		ChoreDto chore = new ChoreDto(name,description,points);
		HttpStatus status=null;
		try {
			ResponseEntity<ChoreDto> response = template.postForEntity(testInfo.getCompleteEndPoint(),chore,ChoreDto.class); 
			status = response.getStatusCode();
		} catch (HttpClientErrorException e) {
			status=e.getStatusCode();
			testInfo.setError(e.getMessage());
		}
		testInfo.setStatus(status);
	}
	
	@When("I create a new chore with name {string}, description {string} and points ")
	public void i_create_a_new_chore_with_name_description_and_points(String name, String description) {
		i_create_a_new_chore_with_name_description_and_points(name,description,null);
	}
	
	@Then("I should be able to find the chore with name {string} on the application")
	public void i_should_be_able_to_find_the_chore_with_name_on_the_application(String name) {
	    assertThat(testInfo.getStatus()).isEqualTo(HttpStatus.CREATED);
	    assertTrue(!choreRepository.findByName(name).isEmpty());
	}
	
}
