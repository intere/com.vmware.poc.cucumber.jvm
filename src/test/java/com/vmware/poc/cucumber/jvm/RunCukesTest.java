package com.vmware.poc.cucumber.jvm;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "classpath:features",
		glue = {"com.vmware.poc.cucumber.jvm.steps"},
		format = {"pretty", "html:target/cucumber-html-report", "json-pretty:target/cucumber-report.json"})
public class RunCukesTest {

}
