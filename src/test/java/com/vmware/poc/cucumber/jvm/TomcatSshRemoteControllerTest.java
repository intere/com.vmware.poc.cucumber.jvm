package com.vmware.poc.cucumber.jvm;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@Ignore("We really only care about the Cucumber Tests")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/cucumber.xml"})
public class TomcatSshRemoteControllerTest {
	
	private static final Logger LOG = Logger.getLogger(TomcatSshRemoteControllerTest.class);


	@Autowired
	private ServerConfig serverConfig;
	private TomcatSshRemoteController controllerSsh;
	
	@Before
	public void setUp() throws Exception {
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
