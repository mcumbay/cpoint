package com.dfwcomputech.cpoint.service.acceptance.users;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.repository.UserChoreRepository;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.acceptance.CurrentTestInfo;
import com.dfwcomputech.cpoint.service.resource.dto.AssignChoreParam;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// User Domain
public class UserStepDef {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserChoreRepository userChoreRepository;

	@Autowired
	private CurrentTestInfo testInfo;
	
	private RestTemplate template = new RestTemplate();
	
	@Given("A user with userName {string} and password {string} already exists")
	public void a_user_already_exists(String userName,String password) {
		User user = new User(userName, password);
		userRepository.save(user);
	}
	
	@When("I create a new user with User Name {string} and password {string}")
    public void the_user_issues_post__create_user(final String userName, final String password) {      
      UserDto user = new UserDto(userName,password);
      HttpStatus status=null;
      try {
    	  ResponseEntity<UserDto> response = template.postForEntity(testInfo.getCompleteEndPoint(),user,UserDto.class);
    	  status = response.getStatusCode();
		} catch (HttpClientErrorException e) {			
			status=e.getStatusCode();
			testInfo.setError(e.getMessage());
		}
      testInfo.setStatus(status);      
    }
	
	@When("I assign the chore {string} to the User {string} for {string}")
	public void i_assign_the_chore_to_the_user(String choreName, String userName, String dateStr) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
	    AssignChoreParam param = new AssignChoreParam(userName,choreName,date);
	    HttpStatus status=null;
	    try {
	    	ResponseEntity<?> response = template.postForEntity(testInfo.getCompleteEndPoint(), param, null);
	    	status = response.getStatusCode();
		} catch (HttpClientErrorException e) {
			status=e.getStatusCode();
			testInfo.setError(e.getMessage());
		}
	    testInfo.setStatus(status);
	}
	
	@Then("I should be able to find the user with User Name {string} on the application")
    public void i_should_be_able_to_find_the_newly_created_user(final String userName) {
	    assertThat(testInfo.getStatus()).isEqualTo(HttpStatus.CREATED);
        assertNotNull(userRepository.findFirstByUserName(userName));
    }
	
	@Then("I should be able to find the chore {string} on {string} list of chores for {string}")
	public void i_should_be_able_to_find_the_chore_on_list_of_chores(String choreName, String userName, String dateStr) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		assertThat(testInfo.getStatus()).isEqualTo(HttpStatus.OK);
		assertNotNull(userChoreRepository.findFirstByUserUserNameAndChoreNameAndDate(userName,choreName,date));
	}
	
}
