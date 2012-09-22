package com.vmware.poc.cucumber.jvm.remote.ssh;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.vmware.poc.cucumber.jvm.models.ProcessInfo;

@Ignore("This unit test needs to be made to work locally without the need for a remote server.")
public class RemoteProcessListingTest {
	private ServerConfig serverConfig;
	private RemoteProcessListing listing;

	@Before
	public void setUp() throws Exception {
		serverConfig = new ServerConfig();
		serverConfig.setHost("localhost");
		serverConfig.setPassword("vmware1");

		listing = new RemoteProcessListing(serverConfig);
	}

	@Test
	public void test() throws Exception {
		assertNotNull("The Process Output was null", listing.getProcessOutput());

		System.out.println(listing.getProcessOutput());
		
		List<ProcessInfo> sshProcesses = listing.getProcessDetailsByName(".*cron.*");
		
		assertTrue("There were no cron processes", sshProcesses.size() > 0);
	}
	
	@Test
	public void testGetAllProcesses() throws Exception {
		List<ProcessInfo> processes = listing.getAllProcesses();
		
		assertNotNull("The Processes are null", processes);
		assertTrue("There are no processes", processes.size() > 0);
	}

}
