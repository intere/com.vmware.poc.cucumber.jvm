package com.vmware.poc.cucumber.jvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.intere.spring.process.ProcessModel;
import com.intere.spring.process.tools.BlockUntilProcessModelEvent;

/**
 * 
 * @author einternicola
 * 
 */
public abstract class AbstractRemoteProcessRunner {
	
	private static final Logger LOG = Logger.getLogger(AbstractRemoteProcessRunner.class);

	private static final String SSH = "ssh";

	protected TestConfig testConfig;
	protected ProcessModel model;
	protected BlockUntilProcessModelEvent listener;

	public AbstractRemoteProcessRunner(TestConfig testConfig) {
		this.testConfig = testConfig;
	}

	public void run() throws IOException, InterruptedException {
		model = new ProcessModel(buildSshCommand());
		LOG.info("About to execute command: " + model.getExecutionString());
		listener = new BlockUntilProcessModelEvent(model, true, true);
		model.runProcess(true);
	}
	
	public List<String> buildSshCommand()
	{
		ArrayList<String> sshCommand = new ArrayList<String>();
		sshCommand.add(SSH);

		if (testConfig.getUser() != null) {
			sshCommand.add(testConfig.getUser() + "@" + testConfig.getHost());
		} else {
			sshCommand.add(testConfig.getHost());
		}
		
		sshCommand.addAll(buildCommand());
		
		return sshCommand;
	}

	public abstract List<String> buildCommand();

	/** Helper method that gives you the process output. */
	public String getProcessOutput() {
		return listener.getStdOutBuffer().toString();
	}

}
