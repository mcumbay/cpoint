package com.dfwcomputech.cpoint.ui.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
//import io.cucumber.junit.platform.engine.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;

//@Cucumber
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		plugin = {"pretty"},
		features = "src/test/resources/features/", 
		glue="com.dfwcomputech.cpoint.ui")
public class AcceptanceTest {

}
