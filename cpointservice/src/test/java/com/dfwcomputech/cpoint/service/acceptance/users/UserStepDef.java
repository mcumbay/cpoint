package com.dfwcomputech.cpoint.service.acceptance.users;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.dfwcomputech.cpoint.integration.model.User;
import com.dfwcomputech.cpoint.integration.repository.UserRepository;
import com.dfwcomputech.cpoint.service.acceptance.ResponseStatusInformation;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;
import com.dfwcomputech.cpoint.service.util.HttpClient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStepDef {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpClient<UserDto> userHttpClient;
	
	@Autowired
	private ResponseStatusInformation statusInfo;
	
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
    	  status = userHttpClient.put(user);    	  
		} catch (HttpClientErrorException e) {			
			status=e.getStatusCode();
			statusInfo.setError(e.getMessage());
		}
      statusInfo.setStatus(status);      
    }
	
	@When("I assign the chore {string} to the User {string}")
	public void i_assign_the_chore_to_the_user(String chore, String userName) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("I should be able to find the user with User Name {string} on the application")
    public void i_should_be_able_to_find_the_newly_created_user(final String userName) {
	    assertThat(statusInfo.getStatus()).isEqualTo(HttpStatus.CREATED);
        assertNotNull(userRepository.findFirstByUserName(userName));
    }
	
	@Then("An error message {string} is shown")
	public void an_error_message_is_shown(String expectedMessage) {
	    assertThat(statusInfo.getStatus()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
	    assertTrue(statusInfo.getError().contains(expectedMessage));
	}
}
