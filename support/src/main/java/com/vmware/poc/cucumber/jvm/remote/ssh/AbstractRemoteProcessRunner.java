package com.vmware.poc.cucumber.jvm.remote.ssh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;

/**
 * 
 * @author einternicola
 * 
 */
public abstract class AbstractRemoteProcessRunner {
	private static final String SSH = "ssh";
	protected ServerConfig serverConfig;
	protected SshRunner runner;


	public AbstractRemoteProcessRunner(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public void run() {
		runner = new SshRunner(serverConfig, getCommand());
		try {
			runner.run();
		} catch (IOException e) {
			throw new RuntimeException("Exception running ssh command:", e);
		}
	}

	public List<String> buildSshCommand() {
		ArrayList<String> sshCommand = new ArrayList<String>();
		sshCommand.add(SSH);

		if (serverConfig.getUsername() != null) {
			sshCommand.add(serverConfig.getUsername() + "@" + serverConfig.getHost());
		} else {
			sshCommand.add(serverConfig.getHost());
		}

		sshCommand.addAll(buildCommand());

		return sshCommand;
	}

	public String getCommand() {
		StringBuilder builder = new StringBuilder();
		List<String> params = buildCommand();

		for (String param : params) {
			if (builder.length() > 0) {
				builder.append(" ");
			}

			builder.append(param);
		}

		return builder.toString();
	}

	public abstract List<String> buildCommand();

	/** Helper method that gives you the process output. */
	public String getProcessOutput() {
		return runner.getStdOut().toString();
	}
}
