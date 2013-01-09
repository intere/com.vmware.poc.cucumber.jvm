package com.vmware.poc.cucumber.jvm.steps.web;

import com.vmware.poc.cucumber.jvm.models.HttpStatusCodes;
import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import cucumber.annotation.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class HttpUrlStepDefs {
	private static final Logger LOG = Logger.getLogger(HttpUrlStepDefs.class);
	
	@Autowired
	private ServerConfig config;

	@Autowired
	private HttpStatusCodes statusCodes;

	@Then("^the \"([^\"]*)\" page for \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void page_for_server_should_be_available(String page, String server, String status) throws Throwable {
//		String url = config.getUrlForPage(page);
//		HttpGet httpGet = new HttpGet(url);
//
//		int expectedStatusCode = statusCodes.get(status);
//
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		HttpResponse response = httpClient.execute(httpGet);
//
//		try {
//			int statusCode = response.getStatusLine().getStatusCode();
//			assertEquals("Expected HTTP status code was not returned: ", expectedStatusCode, statusCode);
//
//			EntityUtils.consume(response.getEntity());
//		} finally {
//			httpGet.releaseConnection();
//		}
		
		LOG.info("Then Step: the \"" + page + "\" page for \"" + server + "\" should be \"" + status + "\"");
	}
}
