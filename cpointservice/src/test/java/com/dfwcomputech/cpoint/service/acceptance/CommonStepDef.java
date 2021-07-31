package com.dfwcomputech.cpoint.service.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonStepDef {

	@Autowired
	private CurrentTestInfo testInfo;
	
	private String serverUrl = "http://localhost";
	@LocalServerPort
	private int port;

	public String baseUrl() {
		return serverUrl + ":" + port;
	}

	@Autowired
	private CurrentTestInfo statusInfo;

	@Given("I have access the url {string}")
	public void i_have_access_the_url(String endPointUrl) {
		log.info("==============================> Setting url test to {}", endPointUrl);
		statusInfo.setCompleteEndPoint(baseUrl() + endPointUrl);
	}
	
	@Then("An error message {string} is shown")
	public void an_error_message_is_shown(String expectedMessage) {
	    assertThat(testInfo.getStatus()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
	    assertTrue(testInfo.getError().contains(expectedMessage));
	}

}
