package com.vmware.poc.cucumber.jvm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/com/vmware/poc/cucumber/jvm/poc-config.xml"})
public class CatRemoteFileTest {
	
	public static final String TEST_FILE = "/etc/hosts";
	@Autowired
	private TestConfig testConfig;

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCatFile() throws Exception {
		CatRemoteFile cat = new CatRemoteFile(testConfig, TEST_FILE);
		cat.run();
		
		assertNotNull("The command failed to give us any output", cat.getProcessOutput());
		System.out.println(cat.getProcessOutput());
	}

}