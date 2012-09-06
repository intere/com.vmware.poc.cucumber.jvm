package com.vmware.poc.cucumber.jvm;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author einternicola
 *
 */
public class TestConfig {
	
	private String host;
	private String user;
	private String tomcatHome;
	
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
	
	public String getTomcatHome() {
		return tomcatHome;
	}
	
	public void setTomcatHome(String tomcatHome) {
		this.tomcatHome = tomcatHome;
	}
}
