package com.vmware.poc.cucumber.jvm.steps;

import com.vmware.poc.cucumber.jvm.RemoteController;
import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.ServerConfig;
import com.vmware.poc.cucumber.jvm.models.RemoteControllerMap;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * @author einternicola
 */
public class ServerStartupStepDefs {
	private static final Logger LOG = Logger.getLogger(ServerStartupStepDefs.class);

	@Autowired
	private RemoteControllerMap remoteControllers;

	@Autowired
	private ServerConfig config;

	@Given("^\"([^\"]*)\" is not running$")
	public void Server_is_not_running(String server) throws Throwable {
		RemoteController remoteController = remoteControllers.get(server);

		RemoteProcessListing listing = new RemoteProcessListing(config);

		if (listing.hasProcessByRegex(remoteController.getProcessName())) {
			LOG.info("Server is running on host: " + config.getHost() + ", shutting down");

			remoteController.stop();
		}
	}

	@When("^\"([^\"]*)\" is started$")
	public void I_tell_Server_to_startup(String server) throws Throwable {
		LOG.info("Starting up " + server + " on host: " + config.getHost());

		RemoteController remoteController = remoteControllers.get(server);

		remoteController.start();
	}

	@Then("^\"([^\"]*)\" should be running$")
	public void Server_should_be_running(String server) throws Throwable {
		RemoteProcessListing listing = new RemoteProcessListing(config);

		RemoteController remoteController = remoteControllers.get(server);

		assertTrue("Server " + server + " is not running on host: " + config.getHost(),
				listing.hasProcessByRegex(remoteController.getProcessName()));
	}
}
