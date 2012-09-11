package com.vmware.poc.cucumber.jvm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author einternicola
 * 
 */
public class TomcatRemoteController extends AbstractRemoteController {

	/** What method are we going to run?. See the enum at the bottom. */
	private RunMethod method;

	private static final String TOMCAT_PROCESS = ".*tomcat.*";

	public TomcatRemoteController(TestConfig testConfig) {
		super(testConfig);
	}

	@Override
	public String getProcessName() {
		return TOMCAT_PROCESS;
	}

	@Override
	public void start() throws IOException, InterruptedException {
		run(RunMethod.Start);
	}

	@Override
	public void stop() throws IOException, InterruptedException {
		run(RunMethod.Stop);
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

		if (testConfig.getTomcatHome() == null) {
			throw new IllegalStateException("Error: The Tomcat Home is not defined in the configuration");
		}

		ArrayList<String> commands = new ArrayList<String>();

		commands.add(testConfig.getTomcatHome() + File.separator + "bin" + File.separator + this.method.getScript());

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
