package com.vmware.poc.cucumber.jvm.models;

import java.util.HashMap;

public class HttpStatusCodes extends HashMap<String, Integer> {
	@Override
	public Integer get(Object status) {
		if (!containsKey(status)) {
			throw new IllegalArgumentException("HTTP Status " + status + " is not configured.");
		}

		return super.get(status);
	}
}
