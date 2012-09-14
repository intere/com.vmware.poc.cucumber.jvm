package com.vmware.poc.cucumber.jvm.models;

public class ProcessInfo {
	//
	// Sample Output:
	// 1000     19303 19186  0 14:54 ?        00:00:00 sshd: einternicola@pts/11
	//
	
	private String uid;
	private Long processId;
	private Long parentId;
	private Long cpuUtilization;
	private String startTime;
	private String tty;
	private String time;
	private String cmd;
	
	
	public ProcessInfo(String line) {
		line = line.replaceAll("  *", " ");
		
		String []parts = line.split(" ");
		
		setUid(parts[0]);
		setProcessId(Long.parseLong(parts[1]));
		setParentId(Long.parseLong(parts[2]));
		setCpuUtilization(Long.parseLong(parts[3]));
		setStartTime(parts[4]);
		setTty(parts[5]);
		setTime(parts[6]);
		setCmd(getRemainder(parts));
	}

	/**
	 * Helper method to build the command portion of the string from the remainder of the parts.
	 * @param parts
	 * @return
	 */
	private String getRemainder(String[] parts) {
		
		StringBuilder builder = new StringBuilder();
		
		for(int i=7;i<parts.length; i++) {
			if(i!=7) {
				builder.append(" ");
			}
			builder.append(parts[i]);
		}
		
		return builder.toString();
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public Long getProcessId() {
		return processId;
	}


	public void setProcessId(Long processId) {
		this.processId = processId;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public Long getCpuUtilization() {
		return cpuUtilization;
	}


	public void setCpuUtilization(Long cpuUtilization) {
		this.cpuUtilization = cpuUtilization;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getTty() {
		return tty;
	}


	public void setTty(String tty) {
		this.tty = tty;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getCmd() {
		return cmd;
	}


	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	
	@Override
	public String toString() {
		return "PID=" + getProcessId() + ", " + getCmd(); 
	}
	
}
