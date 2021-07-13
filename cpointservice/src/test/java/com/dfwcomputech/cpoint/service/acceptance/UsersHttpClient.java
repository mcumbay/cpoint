package com.dfwcomputech.cpoint.service.acceptance;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dfwcomputech.cpoint.service.resource.dto.UserDto;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class UsersHttpClient {
	
    private final String SERVER_URL = "http://localhost";
    private final String USERS_ENDPOINT = "/users";

    @LocalServerPort
    private int port;
    
    private final RestTemplate restTemplate = new RestTemplate();

    private String usersEndpoint() {
        return SERVER_URL + ":" + port + USERS_ENDPOINT;
    }

    public HttpStatus put(final UserDto user) throws RestClientException{
    	 ResponseEntity<UserDto> response = restTemplate.postForEntity(usersEndpoint(), user, UserDto.class);    	 
        return response.getStatusCode();
    }

    public UserDto get(String userName) throws RestClientException{
        return restTemplate.getForEntity(usersEndpoint(), UserDto.class).getBody();
    }

    public void clean() {
        restTemplate.delete(usersEndpoint());
    }
}
