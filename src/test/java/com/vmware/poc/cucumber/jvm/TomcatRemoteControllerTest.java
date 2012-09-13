package com.vmware.poc.cucumber.jvm;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@Ignore("We really only care about the Cucumber Tests")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/cucumber.xml"})
public class TomcatRemoteControllerTest {
	
	private static final Logger LOG = Logger.getLogger(TomcatRemoteControllerTest.class);


	@Autowired
	private ServerConfig serverConfig;
	private TomcatRemoteController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new TomcatRemoteController(serverConfig);
	}

	@Test
	public void testStop() throws Exception {
		
		LOG.info("------------------------------------------------");
		LOG.info("Stopping...");
		LOG.info("------------------------------------------------");
		controller.stop();
		LOG.info(controller.getProcessOutput());		
	}
	
	@Test
	public void testStart() throws Exception {
		LOG.info("------------------------------------------------");
		LOG.info("Starting...");
		LOG.info("------------------------------------------------");
		controller.start();
		LOG.info(controller.getProcessOutput());
	}

}
