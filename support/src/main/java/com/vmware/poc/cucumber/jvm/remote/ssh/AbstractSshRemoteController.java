package com.vmware.poc.cucumber.jvm.remote.ssh;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import com.vmware.poc.cucumber.jvm.remote.RemoteController;

public abstract class AbstractSshRemoteController extends AbstractRemoteProcessRunner implements RemoteController {
	public AbstractSshRemoteController(ServerConfig serverConfig) {
		super(serverConfig);
	}
}
