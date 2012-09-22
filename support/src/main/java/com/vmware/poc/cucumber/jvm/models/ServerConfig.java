package com.vmware.poc.cucumber.jvm.models;

import java.util.Map;

/**
 * @author einternicola
 */
public class ServerConfig {

	private String host;
	private String username;
	private String password;
	private Integer port;
	private String homeDirectory;
	private Map<String, String> urls;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}

	public boolean hasPort() {
		return getPort() != null;
	}

	public boolean hasUsername() {
		return getUsername() != null;
	}

	public boolean hasPassword() {
		return getPassword() != null;
	}
}
