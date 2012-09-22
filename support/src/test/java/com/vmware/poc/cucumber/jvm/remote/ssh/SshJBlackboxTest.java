package com.vmware.poc.cucumber.jvm.remote.ssh;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.connection.channel.direct.Session;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SshJBlackboxTest {

	private static final Logger LOG = Logger.getLogger(SshJBlackboxTest.class);

	/**
	 * This method is used to demonstrate the SSHJ APIs.
	 * @throws Exception
	 */
	@Test
	public void testSshJ() throws Exception {
		final SSHClient ssh = new SSHClient();
		ssh.loadKnownHosts();
		ssh.connect("gemfire-1");
//		String path = System.getProperty("user.home") + File.separator + ".ssh" + File.separator + "id_dsa.public";
//		ssh.authPublickey("einternicola", path);
		ssh.authPassword("einternicola", "vmware1");
		Session session = ssh.startSession();
		
		Command cmd = session.exec("ls -la");
				
		final InputStream in = cmd.getInputStream();
		final InputStream err = cmd.getErrorStream();
		final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		final ByteArrayOutputStream stderr = new ByteArrayOutputStream();
		
		
		Thread t1 = new Thread() {
			public void run() {
				readBufferTillDone(in, stdout);
			};
		};
		Thread t2 = new Thread() {
			public void run() {
				readBufferTillDone(err, stderr);
			}
		};
		t1.start();
		t2.start();
		
		cmd.join();
		
		assertTrue("There was no output", stdout.toString().length()>0);
		LOG.info("Output: '" + stdout.toString() + "'");
	}
	
	
	protected void readBufferTillDone(final InputStream in, final ByteArrayOutputStream out) {
		
		int bytes;
		byte buff[] = new byte[1000];
		
		try {
			while((bytes = in.read(buff))!=-1) {
				out.write(buff, 0, bytes);
			}
		} catch (IOException e) {
			LOG.error("Error reading from stream", e);
		}
		
	}

}
