package com.vmware.poc.cucumber.jvm;

import com.vmware.poc.cucumber.jvm.TestConfig;
import com.vmware.poc.cucumber.jvm.spring.SpringLoader;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;


/**
 * 
 * 
 * @author einternicola
 */
public class TomcatStartupStepDefs {
	
	private TestConfig config;

	@Given("^Tomcat is not running on \"([^\"]*)\"$")
	public void Tomcat_is_running(String host) {
		
		// get the config
		config = SpringLoader.loadTestConfigByName(host);
		
		// TODO - check to see if tomcat is running
		

		// TODO - if it is, then kill it
	}
	
//	@When("\"([^\"]*)\"$")
	@When("^I tell Tomcat to startup$")
	public void I_tell_Tomcat_to_startup() {
		// TODO - startup tomcat on the host
		
		throw new IllegalStateException("Not yet implmented");
	}
	
	@And("^wait for 10 seconds$")
	public void wait_for_10_seconds() throws Throwable {
		Thread.sleep(10000L);
	}
	
	@Then("^Tomcat should be running$")
	public void Tomcat_should_be_running() {
		throw new IllegalStateException("Not yet implmented");		
	}
}
