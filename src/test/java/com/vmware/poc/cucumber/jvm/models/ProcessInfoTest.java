package com.vmware.poc.cucumber.jvm.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProcessInfoTest {
	
	private static final String TEST_LINE_1 = "1000     19303 19186  0 14:54 ?        00:00:00 sshd: einternicola@pts/11";
	private static final String TEST_LINE_2 = "root       887     1  0 Sep03 ?        00:00:00 acpid -c /etc/acpi/events -s /var/run/acpid.socket";
	private static final String TEST_LINE_3 = "1000      1728  1510  0 Sep03 ?        00:00:36 /opt/google/chrome/chrome --type=renderer --lang=en-US --force-fieldtrials=ConnCountImpact/conn_count_6/ConnnectBackupJobs/ConnectBackupJobsEnabled/DnsImpact/default_enabled_prefetch/GlobalSdch/global_enable_sdch/IdleSktToImpact/idle_timeout_10/OmniboxDisallowInlineHQP/Standard/OmniboxHQPNewScoring/Standard/OmniboxSearchSuggest/7/Prerender/ContentPrefetchPrerender2/PrerenderFromOmnibox/OmniboxPrerenderEnabled/ProxyConnectionImpact/proxy_connections_32/SpdyCwnd/cwndMin16/SpdyImpact/spdy3/UMA-Dynamic-Binary-Uniformity-Trial/default/UMA-Uniformity-Trial-1-Percent/group_72/UMA-Uniformity-Trial-10-Percent/group_05/UMA-Uniformity-Trial-20-Percent/group_02/UMA-Uniformity-Trial-5-Percent/default/UMA-Uniformity-Trial-50-Percent/group_01/WarmSocketImpact/warmest_socket/ --renderer-print-preview --disable-accelerated-2d-canvas --channel=1493.3.239697054";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		ProcessInfo testLine1 = new ProcessInfo(TEST_LINE_1);
		
		assertEquals("The UID doesn't match up", "1000", testLine1.getUid());
		assertEquals("The Process ID doesn't match", 19303L, testLine1.getProcessId().longValue());
		assertEquals("The Parent ID doesn't match", 19186L, testLine1.getParentId().longValue());
		assertEquals("The CPU utilization doesn't match", 0L, testLine1.getCpuUtilization().intValue());
		assertEquals("The Start Time doesn't match", "14:54", testLine1.getStartTime());
		assertEquals("The TTY doesn't match", "?", testLine1.getTty());
		assertEquals("The Time doesn't match", "00:00:00", testLine1.getTime());
		assertEquals("The command doesn't match up", "sshd: einternicola@pts/11", testLine1.getCmd());
		
		ProcessInfo testLine2 = new ProcessInfo(TEST_LINE_2);
		assertEquals("The 2nd command doesn't match up", "acpid -c /etc/acpi/events -s /var/run/acpid.socket", testLine2.getCmd());

		ProcessInfo testLine3 = new ProcessInfo(TEST_LINE_3);
		assertEquals("The 3rd command doesn't match up", "/opt/google/chrome/chrome --type=renderer --lang=en-US " + 
				"--force-fieldtrials=ConnCountImpact/conn_count_6/ConnnectBackupJobs/ConnectBackupJobsEnabled/DnsImpact/default_enabled_prefetch" + 
				"/GlobalSdch/global_enable_sdch/IdleSktToImpact/idle_timeout_10/OmniboxDisallowInlineHQP/Standard/OmniboxHQPNewScoring/Standard/" + 
				"OmniboxSearchSuggest/7/Prerender/ContentPrefetchPrerender2/PrerenderFromOmnibox/OmniboxPrerenderEnabled/ProxyConnectionImpact/" + 
				"proxy_connections_32/SpdyCwnd/cwndMin16/SpdyImpact/spdy3/UMA-Dynamic-Binary-Uniformity-Trial/default/UMA-Uniformity-Trial-1-" + 
				"Percent/group_72/UMA-Uniformity-Trial-10-Percent/group_05/UMA-Uniformity-Trial-20-Percent/group_02/UMA-Uniformity-Trial-5-" + 
				"Percent/default/UMA-Uniformity-Trial-50-Percent/group_01/WarmSocketImpact/warmest_socket/ --renderer-print-preview " + 
				"--disable-accelerated-2d-canvas --channel=1493.3.239697054", testLine3.getCmd());
	}

}
