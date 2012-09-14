package com.vmware.poc.cucumber.jvm.config;

import com.vmware.poc.cucumber.jvm.models.HttpStatusCodes;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpUrlConfig {
	@Bean
	public HttpStatusCodes statusCodes() {
		HttpStatusCodes statusCodes = new HttpStatusCodes();

		statusCodes.put("available", HttpStatus.SC_OK);
		statusCodes.put("unavailable", HttpStatus.SC_NOT_FOUND);

		return statusCodes;
	}
}
