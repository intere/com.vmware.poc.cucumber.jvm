package com.vmware.poc.cucumber.jvm.remote.ssh;

import static org.junit.Assert.*;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import org.junit.Before;
import org.junit.Test;

public class CatRemoteFileTest {
	public static final String TEST_FILE = "/etc/hosts";

	private ServerConfig serverConfig;

	@Before
	public void setUp() throws Exception {
		serverConfig = new ServerConfig();
		serverConfig.setHost("localhost");
	}

	@Test
	public void testCatFile() throws Exception {
		CatRemoteFile cat = new CatRemoteFile(serverConfig, TEST_FILE);
		cat.run();
		
		assertNotNull("The command failed to give us any output", cat.getProcessOutput());
	}
}
