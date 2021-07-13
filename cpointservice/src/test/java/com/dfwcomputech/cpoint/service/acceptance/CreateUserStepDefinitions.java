package com.dfwcomputech.cpoint.service.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateUserStepDefinitions {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsersHttpClient httpClient;
	
	private HttpStatus statusCode;
	
	private String error;
	
	@When("I create a new user with User Name {string} and password {string}")
    public void the_user_issues_post__create_user(final String userName, final String password) {
      log.debug("I create a new user with User Name "+userName+" and password "+password);
      //Arrange
      UserDto user = new UserDto(userName,password);
      //Action
      try {
    	  statusCode = httpClient.put(user);    	  
		} catch (HttpClientErrorException e) {			
			statusCode=e.getStatusCode();
			error=e.getMessage();
		}
      log.debug("Status Code = {}",statusCode);
    }
	
	@Then("I should be able to find the user with User Name {string} on the application")
    public void i_should_be_able_to_find_the_newly_created_user(final String userName) {
	    assertThat(statusCode).isEqualTo(HttpStatus.CREATED);
        assertNotNull(userRepository.findFirstByUserName(userName));
    }
	
	@Given("A user with userName {string} and password {string} already exists")
	public void a_user_already_exists(String userName,String password) {
		User user = new User(userName, password);
		userRepository.save(user);
	}

	@Then("An error message {string} is shown")
	public void an_error_message_is_shown(String expectedMessage) {
	    assertThat(statusCode).isEqualTo(HttpStatus.PRECONDITION_FAILED);
	    assertTrue(error.contains(expectedMessage));
	}


}
