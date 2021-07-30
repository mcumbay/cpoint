package com.dfwcomputech.cpoint.service.config;

import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
	
	//Use Cucumber Spring to share state between steps 
	//in a scenario and access the spring application context.
}
