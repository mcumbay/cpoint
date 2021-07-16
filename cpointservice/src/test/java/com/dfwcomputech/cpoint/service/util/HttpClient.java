package com.dfwcomputech.cpoint.service.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class HttpClient<D> {
	
	@Getter@Setter
	private Class<D> dtoClass;	
	@Getter@Setter
    private String serverUrl = "http://localhost";
	@Getter@Setter
    private String endpoint;
	@Getter@Setter
    private int port;
        
    private final RestTemplate restTemplate = new RestTemplate();

    public HttpClient(String endPoint,Class<D> dtoClass) {
    	this.endpoint=endPoint;
    	this.dtoClass=dtoClass;    	
    }
    
    private String getCompleteEndpoint() {
        return getServerUrl() + ":" + port + getEndpoint();
    }

    public HttpStatus put(final D user) throws RestClientException{
    	 ResponseEntity<D> response = restTemplate.postForEntity(getCompleteEndpoint(), user, dtoClass);    	 
        return response.getStatusCode();
    }

    public void clean() {
        restTemplate.delete(getCompleteEndpoint());
    }
}
