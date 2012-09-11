package com.vmware.poc.cucumber.jvm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmware.poc.cucumber.jvm.RemoteTomcatController.RunMethod;

@Ignore("We really only care about the Cucumber Tests")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/cucumber.xml"})
public class RemoteTomcatControllerTest {

	@Autowired
	private TestConfig testConfig;
	private RemoteTomcatController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new RemoteTomcatController(testConfig);
	}

	@Test
	public void testStop() throws Exception {
		
		System.out.println("------------------------------------------------");
		System.out.println("Stopping...");
		System.out.println("------------------------------------------------");
		controller.run(RunMethod.Stop);
		System.out.println(controller.getProcessOutput());		
		
		System.out.println("sleeping for 10 seconds");
		Thread.sleep(10000L);
	}
	
	@Test
	public void testStart() throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("Starting...");
		System.out.println("------------------------------------------------");
		controller.run(RunMethod.Start);
		System.out.println(controller.getProcessOutput());
	}

}
