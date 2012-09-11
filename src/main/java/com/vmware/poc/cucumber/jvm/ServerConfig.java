package com.vmware.poc.cucumber.jvm;

/**
 * 
 * @author einternicola
 *
 */
public class ServerConfig {
	
	private String host;
	private String user;
	private String homeDirectory;
	
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
}
