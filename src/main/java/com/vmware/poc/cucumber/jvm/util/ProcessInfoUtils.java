package com.vmware.poc.cucumber.jvm.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.vmware.poc.cucumber.jvm.models.ProcessInfo;

/**
 * Some helper methods to just deal with random stuff related to {@link ProcessInfo} objects.
 * 
 * @author einternicola
 *
 */
public class ProcessInfoUtils {
	
	private static final Logger LOG = Logger.getLogger(ProcessInfoUtils.class);
	
	/**
	 * Helper method that will log all process info for you.
	 * @param processList
	 */
	public static void logProcessInfo(List<ProcessInfo> processList) {
		
		for(ProcessInfo info : processList) {
			LOG.info(info);
		}
	}

}
