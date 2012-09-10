package com.vmware.poc.cucumber.jvm.steps;

import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.RemoteTomcatController;
import com.vmware.poc.cucumber.jvm.RemoteTomcatController.RunMethod;
import com.vmware.poc.cucumber.jvm.TestConfig;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Step definitions for the Tomcat Shutdown hooks.
 * 
 * @author einternicola
 *
 */
@SuppressWarnings("UnusedDeclaration")
public class TomcatShutdownStepDefs {
	private static final Logger LOG = Logger.getLogger(TomcatShutdownStepDefs.class);

	@Autowired
	private TestConfig config;

	private RemoteProcessListing listing;
	private RemoteTomcatController tomcatController;

	@Given("^Tomcat is running on \"([^\"]*)\"$")
	public void Tomcat_is_running_on(String host) throws Throwable {
		listing = new RemoteProcessListing(config);
		tomcatController = new RemoteTomcatController(config);

		if(!listing.hasProcessByRegex(tomcatController.getProcessName())) {
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
		listing = new RemoteProcessListing(config);

		assertFalse("Tomcat should not be running on host: " + config.getHost(),
				listing.hasProcessByRegex(tomcatController.getProcessName()));
	}
}
