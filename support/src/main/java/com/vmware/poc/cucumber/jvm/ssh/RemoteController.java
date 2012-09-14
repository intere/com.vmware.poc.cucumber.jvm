package com.vmware.poc.cucumber.jvm.ssh;

import java.io.IOException;

public interface RemoteController {
	String getProcessName();

	void start() throws IOException, InterruptedException;

	void stop() throws IOException, InterruptedException;
}
