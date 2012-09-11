package com.vmware.poc.cucumber.jvm.steps.util;

import cucumber.annotation.en.And;
import org.apache.log4j.Logger;

public class TimingStepDefs {
	private static final Logger LOG = Logger.getLogger(TimingStepDefs.class);

	@And("^wait for ([\\d]+) seconds$")
	public void wait_for_N_seconds(int seconds) throws Throwable {
		LOG.info("Waiting for " + seconds + " seconds");
		Thread.sleep((long) seconds * 1000L);
	}
}
