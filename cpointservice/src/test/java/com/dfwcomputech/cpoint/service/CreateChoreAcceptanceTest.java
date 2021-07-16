package com.dfwcomputech.cpoint.service;

import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/createChore.feature", 
				plugin = { "pretty","html:target/cucumber/createchore" }, 
				extraGlue = "com.dfwcomputech.cpoint.service.acceptance.createchore")
@Tag("AcceptanceTest")
public class CreateChoreAcceptanceTest {

}
