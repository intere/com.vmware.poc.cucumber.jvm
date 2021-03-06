package com.vmware.poc.cucumber.jvm.remote.ssh;

import java.util.List;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;
import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.models.ProcessInfo;

/**
 * Some helper methods to just deal with random stuff related to
 * {@link ProcessInfo} objects.
 * 
 * @author einternicola
 * 
 */
public class ProcessInfoUtils {

	private static final Logger LOG = Logger.getLogger(ProcessInfoUtils.class);

	/**
	 * Helper method that will log all process info for you.
	 * 
	 * @param processList
	 */
	public static void logProcessInfo(List<ProcessInfo> processList) {

		for (ProcessInfo info : processList) {
			LOG.info(info);
		}
	}

	/**
	 * 
	 * @param config
	 * @param processString
	 * @return
	 */
	public static boolean processIsRunning(ServerConfig config, String processString) {
		RemoteProcessListing listing = new RemoteProcessListing(config);

		return listing.hasProcessByName(".*" + processString + ".*");
	}

	/**
	 * This method will wait for a specific process to start for you or timeout
	 * after the specified timeout.
	 * 
	 * @param config
	 *            The Server Configuration to use (what host to connect to).
	 * @param processString
	 *            The Process to "grep" for.
	 * @param intervalInSeconds
	 *            The polling interval (in seconds); essentially the logic is:
	 *            wait for time interval to elapse, then check to see if the
	 *            process is running.
	 * @param timeoutInSeconds
	 *            How long to wait until we timeout and assume the process is
	 *            not starting.
	 * @param minimumWaitInSeconds
	 *            The minimum amount of time to wait (in seconds).
	 * @return
	 */
	public static boolean waitForProcessToStart(ServerConfig config, String processString, int intervalInSeconds, int timeoutInSeconds, int minimumWaitInSeconds) {

		boolean running = processIsRunning(config, processString);

		if (running) {
			sleep(minimumWaitInSeconds);
			return running;
		}

		int cycles = (timeoutInSeconds / intervalInSeconds) + 1;

		for (int i = 0; i < cycles && !running; i++) {
			LOG.info(processString + " is still not running (waiting for it to start)");
			sleep(intervalInSeconds);
			running = processIsRunning(config, processString);
		}

		if (!running) {
			LOG.error("Timed out after " + timeoutInSeconds + " seconds waiting for process: " + processString + " to start");
		}

		return running;
	}

	/**
	 * This method will wait for a specific process to stop for you, or timeout
	 * after the specified timeout.
	 * 
	 * @param config
	 * @param processString
	 * @param intervalInSeconds
	 * @param timeoutInSeconds
	 * @param minimumWaitInSeconds
	 * @return
	 */
	public static boolean waitForProcessToStop(ServerConfig config, String processString, int intervalInSeconds, int timeoutInSeconds, int minimumWaitInSeconds) {

		boolean running = processIsRunning(config, processString);

		if (!running) {
			sleep(minimumWaitInSeconds);
			return true;
		}

		int cycles = (timeoutInSeconds / intervalInSeconds) + 1;

		for (int i = 0; i < cycles && running; i++) {
			LOG.info(processString + " is still running (waiting for it to stop)");
			sleep(intervalInSeconds);
			running = processIsRunning(config, processString);
		}

		if (running) {
			LOG.error("Timed out after " + timeoutInSeconds + " seconds waiting for process: " + processString + " to stop");
		}

		return running;
	}

	private static void sleep(int seconds) {
		try {
			Thread.sleep(1000L * seconds);
		} catch (InterruptedException e) {
			// ignore
		}
	}

}
