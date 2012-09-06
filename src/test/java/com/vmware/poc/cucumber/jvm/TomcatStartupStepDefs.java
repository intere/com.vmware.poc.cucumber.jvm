package com.vmware.poc.cucumber.jvm;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.RemoteTomcatController.RunMethod;
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
	
	private static final String TOMCAT_PROCESS = ".*tomcat.*";
	
	private static final Logger LOG = Logger.getLogger(TomcatStartupStepDefs.class);
	
	private TestConfig config;
	private RemoteProcessListing listing;
	private RemoteTomcatController tomcatController;
	

	@Given("^Tomcat is not running on \"([^\"]*)\"$")
	public void Tomcat_is_not_running(String host) throws Throwable 
	{		
		// get the config
		config = SpringLoader.loadTestConfigByName(host);
		listing = new RemoteProcessListing(config);
		tomcatController = new RemoteTomcatController(config);
		
		// Check to see if tomcat is running.  If it is, then kill it
		if(listing.hasProcessByRegex(TOMCAT_PROCESS)) {
			LOG.info("Tomcat is running on host: " + config.getHost() + ", shutting down");
			tomcatController.run(RunMethod.Stop);
		}
	}
	
	@When("^I tell Tomcat to startup$")
	public void I_tell_Tomcat_to_startup() throws Throwable 
	{		
		LOG.info("Starting up tomcat on host: " + config.getHost());
		tomcatController.run(RunMethod.Start);
	}
	
	@And("^wait for 10 seconds$")
	public void wait_for_10_seconds() throws Throwable 
	{
		Thread.sleep(10000L);
	}
	
	@Then("^Tomcat should be running$")
	public void Tomcat_should_be_running() throws Throwable 
	{		
		listing = new RemoteProcessListing(config);
		
		assertTrue("Tomcat is not running on host: " + config.getHost(),
				listing.hasProcessByRegex(TOMCAT_PROCESS));
	}
}
