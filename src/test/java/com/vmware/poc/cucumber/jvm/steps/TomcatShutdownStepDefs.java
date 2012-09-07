package com.vmware.poc.cucumber.jvm.steps;

import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.RemoteTomcatController;
import com.vmware.poc.cucumber.jvm.RemoteTomcatController.RunMethod;
import com.vmware.poc.cucumber.jvm.TestConfig;
import com.vmware.poc.cucumber.jvm.spring.SpringLoader;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;


/**
 * Step definitions for the Tomcat Shutdown hooks.
 * 
 * @author einternicola
 *
 */
public class TomcatShutdownStepDefs {
	private static final String TOMCAT_PROCESS = ".*tomcat.*";
	private static final Logger LOG = Logger.getLogger(TomcatShutdownStepDefs.class);

	private TestConfig config;
	private RemoteProcessListing listing;
	private RemoteTomcatController tomcatController;
	
	@Given("^Tomcat is running on \"([^\"]*)\"$")
	public void Tomcat_is_running_on(String host) throws Throwable {
		// get the config
		config = SpringLoader.loadTestConfigByName(host);
		listing = new RemoteProcessListing(config);
		tomcatController = new RemoteTomcatController(config);
		
		if(!listing.hasProcessByRegex(TOMCAT_PROCESS)) {
			LOG.info("Tomcat is not running on host: " + config.getHost() + ", I'll start it up");
			tomcatController.run(RunMethod.Start);
		}
		
	}
	
	@When("^I tell Tomcat to shutdown$")
	public void I_tell_Tomcat_to_shutdown() throws Throwable {
		
		LOG.info("Shutting down Tomcat");
		tomcatController.run(RunMethod.Stop);
		
	}
	
	@Then("^Tomcat should not be running$")
	public void Tomcat_should_not_be_running() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}


}
