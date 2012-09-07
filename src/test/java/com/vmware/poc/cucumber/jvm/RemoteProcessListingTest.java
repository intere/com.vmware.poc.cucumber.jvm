package com.vmware.poc.cucumber.jvm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmware.poc.cucumber.jvm.models.ProcessInfo;

@Ignore("We really only care about the Cucumber Tests")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/com/vmware/poc/cucumber/jvm/poc-config.xml"})
public class RemoteProcessListingTest {
	
	@Autowired
	private TestConfig testConfig;
	private RemoteProcessListing listing;

	@Before
	public void setUp() throws Exception {
		listing = new RemoteProcessListing(testConfig);
	}

	@Test
	public void test() throws Exception {
		
		assertNotNull(listing.getProcessOutput(), "The Process Output was null");
		System.out.println(listing.getProcessOutput());
		
		List<ProcessInfo> sshProcesses = listing.getProcessDetails(".*ssh.*");
		
		assertTrue("There were no ssh processes...", sshProcesses.size()>0);
		
		for(ProcessInfo procInfo : sshProcesses) {
			System.out.println("SSH Process Line: " + procInfo.getProcessId());
		}
	}
	
	@Test
	public void testGetAllProcesses() throws Exception {
		
		List<ProcessInfo> processes = listing.getAllProcesses();
		
		assertNotNull("The Processes are null", processes);
		assertTrue("There are no processes", processes.size()>0);
		
	}

}
