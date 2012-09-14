package com.vmware.poc.cucumber.jvm.ssh;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;

public abstract class AbstractSshRemoteController extends AbstractRemoteProcessRunner implements RemoteController {
	public AbstractSshRemoteController(ServerConfig serverConfig) {
		super(serverConfig);
	}
}
