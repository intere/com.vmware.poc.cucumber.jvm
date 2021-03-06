package com.vmware.poc.cucumber.jvm.remote.ssh;

import java.util.ArrayList;
import java.util.List;

import com.vmware.poc.cucumber.jvm.models.ProcessInfo;
import com.vmware.poc.cucumber.jvm.models.ServerConfig;

/**
 * 
 * @author einternicola
 * 
 */
public class RemoteProcessListing extends AbstractRemoteProcessRunner {

	private static final String PS = "ps";
	private static final String OPTIONS = "-aef";

	/** Constructor that sets the test config and then executes. 
	 * @throws Exception */
	public RemoteProcessListing(ServerConfig serverConfig) {
		super(serverConfig);
		run();
	}

	/** Helper method that actually builds out the command for you. */
	public List<String> buildCommand() {
		List<String> cmd = new ArrayList<String>();

		cmd.add(PS);
		cmd.add(OPTIONS);

		return cmd;
	}

	/**
	 * This method gives you all of the process list as an object.
	 * 
	 * @return
	 */
	public List<ProcessInfo> getAllProcesses() {

		ArrayList<ProcessInfo> processes = new ArrayList<ProcessInfo>();

		String lines[] = getProcessOutput().split("\n");

		boolean skip = true;

		for (String line : lines) {
			if (skip) {
				skip = false;
				continue;
			}

			processes.add(new ProcessInfo(line));
		}

		return processes;
	}

	/**
	 * This method will give you all of the matches for a given process name.
	 * 
	 * @param processName
	 * @return
	 */
	public List<ProcessInfo> getProcessDetailsByName(String processName) {
		return getProcessDetailsByRegex(".*" + processName + ".*");
	}

	/**
	 * This method will give you all of the matches for a given process regex.
	 * @param processRegex
	 * @return
	 */
	public List<ProcessInfo> getProcessDetailsByRegex(String processRegex) {
		ArrayList<ProcessInfo> matches = new ArrayList<ProcessInfo>();

		String lines[] = getProcessOutput().split("\n");

		for (String line : lines) {
			if (line.matches(processRegex)) {
				matches.add(new ProcessInfo(line));
			}
		}

		return matches;
	}

	/**
	 * This method will tell you if the provided process (regex) exists in the
	 * list of processes.
	 * 
	 * @param processName
	 * @return
	 */
	public boolean hasProcessByName(String processName) {
		return getProcessDetailsByName(processName).size() > 0;
	}
}
