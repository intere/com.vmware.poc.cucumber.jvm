package com.vmware.poc.cucumber.jvm.remote.ssh;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("This test doesn't actually verify anything, it just executes and prints results.")
public class TomcatSshRemoteControllerTest {
	private static final Logger LOG = Logger.getLogger(TomcatSshRemoteControllerTest.class);

	private ServerConfig serverConfig;
	private TomcatSshRemoteController controllerSsh;
	
	@Before
	public void setUp() throws Exception {
		serverConfig = new ServerConfig();
		serverConfig.setHost("localhost");

		controllerSsh = new TomcatSshRemoteController(serverConfig);
	}

	@Test
	public void testStop() throws Exception {
		
		LOG.info("------------------------------------------------");
		LOG.info("Stopping...");
		LOG.info("------------------------------------------------");
		controllerSsh.stop();
		LOG.info(controllerSsh.getProcessOutput());
	}
	
	@Test
	public void testStart() throws Exception {
		LOG.info("------------------------------------------------");
		LOG.info("Starting...");
		LOG.info("------------------------------------------------");
		controllerSsh.start();
		LOG.info(controllerSsh.getProcessOutput());
	}

}
