package com.vmware.poc.cucumber.jvm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.util.ProcessInfoUtils;

/**
 * 
 * @author einternicola
 * 
 */
public class TomcatSshRemoteController extends AbstractSshRemoteController {
	
	private static final Logger LOG = Logger.getLogger(TomcatSshRemoteController.class);

	/** What method are we going to run?. See the enum at the bottom. */
	private RunMethod method;

	private static final String TOMCAT_PROCESS = "tomcat";

	public TomcatSshRemoteController(ServerConfig serverConfig) {
		super(serverConfig);
	}

	@Override
	public String getProcessName() {
		return TOMCAT_PROCESS;
	}

	@Override
	public void start() throws IOException, InterruptedException {
		run(RunMethod.Start);
		if(!ProcessInfoUtils.waitForProcessToStart(serverConfig, TOMCAT_PROCESS, 5, 30, 10)) {
			throw new IllegalStateException(TOMCAT_PROCESS + " failed to start");
		} else {
			LOG.info(TOMCAT_PROCESS + " has been started");
		}
	}

	@Override
	public void stop() throws IOException, InterruptedException {
		run(RunMethod.Stop);
		if(!ProcessInfoUtils.waitForProcessToStop(serverConfig, TOMCAT_PROCESS, 5,30, 10)) {
			throw new IllegalStateException(TOMCAT_PROCESS + " failed to shutdown");
		} else {
			LOG.info(TOMCAT_PROCESS + " has been shutdown");
		}
	}

	private void run(RunMethod method) throws IOException, InterruptedException {
		this.method = method;
		run();
		this.method = null;
	}

	@Override
	public void run() throws IOException, InterruptedException {
		if (this.method == null) {
			throw new IllegalStateException("Error, you must use the alternate run(RunMethod) implementation");
		}
		super.run();
	}

	@Override
	public List<String> buildCommand() {

		if (serverConfig.getHomeDirectory() == null) {
			throw new IllegalStateException("Error: The Tomcat Home is not defined in the configuration");
		}

		ArrayList<String> commands = new ArrayList<String>();

		commands.add(serverConfig.getHomeDirectory() + File.separator + "bin" + File.separator + this.method.getScript());

		return commands;
	}

	private enum RunMethod {
		Start("startup.sh"), Stop("shutdown.sh"), Version("version.sh");

		private String script;

		private RunMethod(String script) {
			this.script = script;
		}

		public String getScript() {
			return script;
		}
	}
}
