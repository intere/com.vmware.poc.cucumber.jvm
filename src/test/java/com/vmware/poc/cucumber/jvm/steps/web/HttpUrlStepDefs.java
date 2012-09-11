package com.vmware.poc.cucumber.jvm.steps.web;

import com.vmware.poc.cucumber.jvm.ServerConfig;
import cucumber.annotation.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class HttpUrlStepDefs {
	@Autowired
	private ServerConfig config;

	@Then("^the URL \"([^\"]*)\" should be available$")
	public void url_should_be_available(String url) throws Throwable {
		HttpGet httpGet = new HttpGet(url);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(httpGet);

		try {
			int statusCode = response.getStatusLine().getStatusCode();
			assertEquals(HttpStatus.SC_OK, statusCode);

			EntityUtils.consume(response.getEntity());
		} finally {
			httpGet.releaseConnection();
		}
	}
}
