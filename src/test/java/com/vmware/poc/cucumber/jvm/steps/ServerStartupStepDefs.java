package com.vmware.poc.cucumber.jvm.steps;

import com.vmware.poc.cucumber.jvm.AbstractRemoteController;
import com.vmware.poc.cucumber.jvm.RemoteProcessListing;
import com.vmware.poc.cucumber.jvm.TestConfig;
import com.vmware.poc.cucumber.jvm.models.ProcessInfo;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author einternicola
 */
@SuppressWarnings("UnusedDeclaration")
public class ServerStartupStepDefs {
	private static final Logger LOG = Logger.getLogger(ServerStartupStepDefs.class);

	@Autowired
	private Map<String, AbstractRemoteController> remoteControllersByServerType;

	@Autowired
	private TestConfig config;

	@Given("^\"([^\"]*)\" is not running on \"([^\"]*)\"$")
	public void Server_is_not_running(String server, String host) throws Throwable {
		AbstractRemoteController remoteController = remoteControllersByServerType.get(server);
		assertNotNull(remoteController);

		RemoteProcessListing listing = new RemoteProcessListing(config);

		if (listing.hasProcessByRegex(remoteController.getProcessName())) {
			LOG.info("Server is running on host: " + config.getHost() + ", shutting down");

			List<ProcessInfo> procs = listing.getProcessDetails(remoteController.getProcessName());

			remoteController.stop();
		}
	}

	@When("^I tell \"([^\"]*)\" to startup$")
	public void I_tell_Server_to_startup(String server) throws Throwable {
		LOG.info("Starting up " + server + " on host: " + config.getHost());

		AbstractRemoteController remoteController = remoteControllersByServerType.get(server);
		assertNotNull(remoteController);

		remoteController.start();
	}

	@Then("^\"([^\"]*)\" should be running$")
	public void Server_should_be_running(String server) throws Throwable {
		RemoteProcessListing listing = new RemoteProcessListing(config);

		AbstractRemoteController remoteController = remoteControllersByServerType.get(server);
		assertNotNull(remoteController);

		assertTrue("Server " + server + " is not running on host: " + config.getHost(),
				listing.hasProcessByRegex(remoteController.getProcessName()));
	}
}
