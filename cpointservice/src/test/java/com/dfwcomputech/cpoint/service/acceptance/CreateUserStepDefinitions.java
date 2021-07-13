package com.dfwcomputech.cpoint.service.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
	
	@Given("A user with userName (\\w+) and password (\\w+) already exists")
	public void a_user_already_exists(String userName,String password) {
		User user = new User(userName, password);
		userRepository.save(user);
	}
		
    @When("^I create a new user with User Name (\\w+) and password (\\w+)$")
    public void the_user_issues_post__create_user(final String userName, final String password) {
      log.debug("I create a new user with User Name "+userName+" and password "+password);
      //Arrange
      UserDto user = new UserDto(userName,password);
      //Action
      int statusCode = httpClient.put(user);
      //Assert
      assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value());      
    }

    @Then("^I should be able to find the user with User Name (\\w+) on the application$")
    public void the_bag_should_contain_only_something(final String userName) {
        assertNotNull(userRepository.findFirstByUserName(userName));
    }
}
