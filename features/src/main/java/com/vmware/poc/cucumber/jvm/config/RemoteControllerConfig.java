package com.vmware.poc.cucumber.jvm.config;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import com.vmware.poc.cucumber.jvm.ssh.RemoteController;
import com.vmware.poc.cucumber.jvm.ssh.RemoteControllerMap;
import com.vmware.poc.cucumber.jvm.ssh.TomcatSshRemoteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteControllerConfig {

	@Autowired
	private ServerConfig config;

	@Bean
	public RemoteControllerMap remoteControllers() {
		RemoteControllerMap remoteControllers = new RemoteControllerMap();

		remoteControllers.put("tc Server", tomcatRemoteController());

		return remoteControllers;
	}

	@Bean
	public RemoteController tomcatRemoteController() {
		return new TomcatSshRemoteController(config);
	}
}
