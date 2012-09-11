package com.vmware.poc.cucumber.jvm;

import java.io.IOException;

public abstract class AbstractRemoteController extends AbstractRemoteProcessRunner {
	public AbstractRemoteController(ServerConfig serverConfig) {
		super(serverConfig);
	}

	public abstract String getProcessName();

	public abstract void start() throws IOException, InterruptedException;

	public abstract void stop() throws IOException, InterruptedException;
}
