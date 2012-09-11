package com.vmware.poc.cucumber.jvm.config;

import com.vmware.poc.cucumber.jvm.AbstractRemoteController;
import com.vmware.poc.cucumber.jvm.ServerConfig;
import com.vmware.poc.cucumber.jvm.TomcatRemoteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessConfig {

	@Autowired
	private ServerConfig config;

	@Bean(name = "Tomcat")
	public AbstractRemoteController tomcatRemoteController() {
		return new TomcatRemoteController(config);
	}
}
