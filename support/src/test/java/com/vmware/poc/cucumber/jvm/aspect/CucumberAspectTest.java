package com.vmware.poc.cucumber.jvm.aspect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/aspect-bean.xml")
public class CucumberAspectTest {
	
	@Autowired
	private CucumberAspect aspect;

	@Test
	public void test() {
		assertNotNull("The aspect was not wired in", aspect);
	}

}
