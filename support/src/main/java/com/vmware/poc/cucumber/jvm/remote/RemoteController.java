package com.vmware.poc.cucumber.jvm.remote;


public interface RemoteController {
	String getProcessName();

	void start();

	void stop();
}
