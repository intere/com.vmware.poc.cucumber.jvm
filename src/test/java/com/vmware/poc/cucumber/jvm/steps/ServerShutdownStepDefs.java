package com.vmware.poc.cucumber.jvm.steps;

import com.vmware.poc.cucumber.jvm.AbstractRemoteController;
import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.TestConfig;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Step definitions for the server Shutdown hooks.
 * 
 * @author einternicola
 */
@SuppressWarnings("UnusedDeclaration")
public class ServerShutdownStepDefs {
	private static final Logger LOG = Logger.getLogger(ServerShutdownStepDefs.class);

	@Autowired
	private Map<String, AbstractRemoteController> remoteControllersByServerType;

	@Autowired
	private TestConfig config;

	@Given("^\"([^\"]*)\" is running on \"([^\"]*)\"$")
	public void Server_is_running_on(String server, String host) throws Throwable {
		AbstractRemoteController remoteController = remoteControllersByServerType.get(server);
		assertNotNull(remoteController);

		RemoteProcessListing listing = new RemoteProcessListing(config);

		if(!listing.hasProcessByRegex(remoteController.getProcessName())) {
			LOG.info("Server " + server + " is not running on host: " + config.getHost() + ", I'll start it up");
			remoteController.start();
		}
	}

	@When("^I tell \"([^\"]*)\" to shutdown$")
	public void I_tell_Server_to_shutdown(String server) throws Throwable {
		LOG.info("Shutting down server " + server);

		AbstractRemoteController remoteController = remoteControllersByServerType.get(server);
		assertNotNull(remoteController);

		remoteController.stop();
	}

	@Then("^\"([^\"]*)\" should not be running$")
	public void Server_should_not_be_running(String server) throws Throwable {
		RemoteProcessListing listing = new RemoteProcessListing(config);

		AbstractRemoteController remoteController = remoteControllersByServerType.get(server);
		assertNotNull(remoteController);

		assertFalse("\"Server \" + server + \" should not be running on host: " + config.getHost(),
				listing.hasProcessByRegex(remoteController.getProcessName()));
	}
}
