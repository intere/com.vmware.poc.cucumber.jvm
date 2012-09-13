package com.vmware.poc.cucumber.jvm.steps.process;

import com.vmware.poc.cucumber.jvm.RemoteController;
import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.ServerConfig;
import com.vmware.poc.cucumber.jvm.models.RemoteControllerMap;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;

/**
 * Step definitions for the server Shutdown hooks.
 * 
 * @author einternicola
 */
public class ServerShutdownStepDefs {
	private static final Logger LOG = Logger.getLogger(ServerShutdownStepDefs.class);

	@Autowired
	private RemoteControllerMap remoteControllers;

	@Autowired
	private ServerConfig config;

	@Given("^\"([^\"]*)\" is running$")
	public void server_is_running_on(String server) throws Throwable {
		RemoteController remoteController = remoteControllers.get(server);

		RemoteProcessListing listing = new RemoteProcessListing(config);

		if(!listing.hasProcessByRegex(remoteController.getProcessName())) {
			LOG.info("Server " + server + " is not running on host: " + config.getHost() + ", I'll start it up");
			remoteController.start();
		}
	}

	@When("^\"([^\"]*)\" is stopped$")
	public void server_is_stopped(String server) throws Throwable {
		LOG.info("Shutting down server " + server);

		RemoteController remoteController = remoteControllers.get(server);

		remoteController.stop();
	}

	@Then("^\"([^\"]*)\" should not be running$")
	public void server_should_not_be_running(String server) throws Throwable {
		RemoteProcessListing listing = new RemoteProcessListing(config);

		RemoteController remoteController = remoteControllers.get(server);

		assertFalse("Server " + server + " should not be running on host: " + config.getHost(),
				listing.hasProcessByRegex(remoteController.getProcessName()));
	}
}
