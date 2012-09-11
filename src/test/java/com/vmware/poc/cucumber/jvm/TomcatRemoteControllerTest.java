package com.vmware.poc.cucumber.jvm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore("We really only care about the Cucumber Tests")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/cucumber.xml"})
public class TomcatRemoteControllerTest {

	@Autowired
	private TestConfig testConfig;
	private TomcatRemoteController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new TomcatRemoteController(testConfig);
	}

	@Test
	public void testStop() throws Exception {
		
		System.out.println("------------------------------------------------");
		System.out.println("Stopping...");
		System.out.println("------------------------------------------------");
		controller.stop();
		System.out.println(controller.getProcessOutput());		
		
		System.out.println("sleeping for 10 seconds");
		Thread.sleep(10000L);
	}
	
	@Test
	public void testStart() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("Starting...");
		System.out.println("------------------------------------------------");
		controller.start();
		System.out.println(controller.getProcessOutput());
	}

}
