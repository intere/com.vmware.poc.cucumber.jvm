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
public class CatRemoteFile extends AbstractRemoteProcessRunner {
	
	private File remoteFile;
	
	public CatRemoteFile(TestConfig testConfig, String remoteFile) throws IOException, InterruptedException {
		this(testConfig, new File(remoteFile));
	}

	public CatRemoteFile(TestConfig testConfig, File remoteFile) throws IOException, InterruptedException {
		super(testConfig);
		this.remoteFile = remoteFile;
	}
	
	@Override
	public List<String> buildCommand() {
		ArrayList<String> commands = new ArrayList<String>();
		
		commands.add("cat");
		commands.add(remoteFile.getAbsolutePath());
		
		return commands;
	}
}
