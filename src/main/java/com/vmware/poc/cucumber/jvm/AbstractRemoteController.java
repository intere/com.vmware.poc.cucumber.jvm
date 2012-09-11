package com.vmware.poc.cucumber.jvm;

public abstract class AbstractRemoteController extends AbstractRemoteProcessRunner implements RemoteController {
	public AbstractRemoteController(ServerConfig serverConfig) {
		super(serverConfig);
	}
}
