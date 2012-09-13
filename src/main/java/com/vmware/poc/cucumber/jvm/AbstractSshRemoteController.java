package com.vmware.poc.cucumber.jvm;

public abstract class AbstractSshRemoteController extends AbstractRemoteProcessRunner implements RemoteController {
	public AbstractSshRemoteController(ServerConfig serverConfig) {
		super(serverConfig);
	}
}
