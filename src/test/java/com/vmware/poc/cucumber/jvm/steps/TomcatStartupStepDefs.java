package com.vmware.poc.cucumber.jvm.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.RemoteTomcatController;
import com.vmware.poc.cucumber.jvm.RemoteTomcatController.RunMethod;
import com.vmware.poc.cucumber.jvm.TestConfig;
import com.vmware.poc.cucumber.jvm.models.ProcessInfo;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author einternicola
 */
@SuppressWarnings("UnusedDeclaration")
public class TomcatStartupStepDefs {

	private static final Logger LOG = Logger.getLogger(TomcatStartupStepDefs.class);

	@Autowired
	private TestConfig config;

	private RemoteProcessListing listing;
	private RemoteTomcatController tomcatController;

	@Given("^Tomcat is not running on \"([^\"]*)\"$")
	public void Tomcat_is_not_running(String host) throws Throwable {
		listing = new RemoteProcessListing(config);
		tomcatController = new RemoteTomcatController(config);

		// Check to see if tomcat is running.  If it is, then kill it
		if (listing.hasProcessByRegex(tomcatController.getProcessName())) {
			LOG.info("Tomcat is running on host: " + config.getHost() + ", shutting down");

			List<ProcessInfo> procs = listing.getProcessDetails(tomcatController.getProcessName());

			tomcatController.run(RunMethod.Stop);
		}
	}

	@When("^I tell Tomcat to startup$")
	public void I_tell_Tomcat_to_startup() throws Throwable {
		LOG.info("Starting up tomcat on host: " + config.getHost());
		tomcatController.run(RunMethod.Start);
	}

	@Then("^Tomcat should be running$")
	public void Tomcat_should_be_running() throws Throwable {
		listing = new RemoteProcessListing(config);

		assertTrue("Tomcat is not running on host: " + config.getHost(),
				listing.hasProcessByRegex(tomcatController.getProcessName()));
	}
}
