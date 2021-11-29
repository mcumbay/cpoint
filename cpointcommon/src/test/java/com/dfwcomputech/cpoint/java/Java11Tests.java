package com.dfwcomputech.cpoint.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Java11Tests {

	/*
	 * JEP 321: HTTP Client (Standard)
	 */
	@Test
	void jep312() throws IOException, InterruptedException {
	    HttpClient httpClient = HttpClient.newBuilder()
	            .version(HttpClient.Version.HTTP_1_1)
	            .connectTimeout(Duration.ofSeconds(10))
	            .build();

	    HttpRequest request = HttpRequest.newBuilder()
	            .GET()
	            .uri(URI.create("https://httpbin.org/get"))
	            .setHeader("User-Agent", "Java 11 HttpClient Bot")
	            .build();

	    HttpResponse<String> response =
	      httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	    HttpHeaders headers = response.headers();
	    headers.map().forEach((k, v) -> log.debug(k + ":" + v));

	    log.debug("Response -> {}",response.statusCode());
	    
	    log.debug("Body ->{}",response.body());
	}
	
	/*
	 * JEP 323: Local-Variable Syntax for Lambda Parameters
	 */
	
	@Test
	void jep323() {
		List<String> list = Arrays.asList("a", "b", "c");
		try {
			String result = list.stream().map((@NotNull var x) -> x.toUpperCase()).collect(Collectors.joining(","));
			log.debug("{}", result);
		} catch (Exception e) {
			log.error("{}",e);
		}

	}
	
	/*
	 * JEP 327: Unicode 10
	 */
	@Test
	void jep327() {
		
        String codepoint = "U+1F92A";   // crazy face
        System.out.println(convertCodePoints(codepoint));
  
	}
	
    static char[] convertCodePoints(String codePoint) {
        Integer i = Integer.valueOf(codePoint.substring(2), 16);
        char[] chars = Character.toChars(i);
        return chars;

    }
}
