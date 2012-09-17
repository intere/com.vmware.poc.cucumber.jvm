package com.vmware.poc.cucumber.jvm.remote;

import java.util.HashMap;

public class RemoteControllerMap extends HashMap<String, RemoteController> {
	@Override
	public RemoteController get(Object key) {
		RemoteController remoteController = super.get(key);

		if (remoteController == null) {
			throw new IllegalArgumentException("RemoteController with the name " + key + " is not configured.");
		}

		return remoteController;
	}
}
