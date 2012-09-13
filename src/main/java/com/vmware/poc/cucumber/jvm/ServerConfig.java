package com.vmware.poc.cucumber.jvm;

import java.util.Map;

/**
 * @author einternicola
 */
public class ServerConfig {

	private String host;
	private String user;
	private String homeDirectory;
	private Map<String, String> urls;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getHomeDirectory() {
		return homeDirectory;
	}

	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public Map<String, String> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}

	public String getUrlForPage(String page) {
		if (!urls.containsKey(page)) {
			throw new IllegalArgumentException("No URL for page " + page + " is defined.");
		}

		return urls.get(page);
	}
}
