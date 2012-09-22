package com.vmware.poc.cucumber.jvm.remote.ssh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

import com.vmware.poc.cucumber.jvm.models.ServerConfig;

/**
 * This class is responsible for executing a remote command and handing you back
 * the response.
 * 
 * @author einternicola
 * 
 *         TODO: Add event registration so we can execute a command and be
 *         notified when it completes, rather than blocking and waiting for it.
 * 
 */
public class SshRunner {
	
	private static final Logger LOG = Logger.getLogger(SshRunner.class);


	private ByteArrayOutputStream stdOut;
	private ByteArrayOutputStream stdErr;
	private ServerConfig config;
	private String command;
	// The ssh configuration as mined out of the configuration and/or
	private int port;
	private String host;
	private String username;
	private String password;

	/**
	 * Constructor.
	 * 
	 * @param config
	 * @param command
	 */
	public SshRunner(ServerConfig config, String command) {
		this.config = config;
		this.command = command;
	}

	public void run() throws IOException {
		configure();
		SSHClient ssh = new SSHClient();
		ssh.loadKnownHosts();
		ssh.connect(host, port);
		ssh.authPassword(username, password);
		Session session = ssh.startSession();
		Command cmd = session.exec(command);

		final InputStream in = cmd.getInputStream();
		final InputStream err = cmd.getErrorStream();
		stdOut = new ByteArrayOutputStream();
		stdErr = new ByteArrayOutputStream();

		Thread t1 = new Thread() {
			public void run() {
				readBufferTillDone(in, stdOut);
			};
		};
		Thread t2 = new Thread() {
			public void run() {
				readBufferTillDone(err, stdErr);
			}
		};
		t1.start();
		t2.start();

		cmd.join();

	}

	public ByteArrayOutputStream getStdOut() {
		return stdOut;
	}

	public ByteArrayOutputStream getStdErr() {
		return stdErr;
	}

	protected void readBufferTillDone(final InputStream in, final ByteArrayOutputStream out) {

		int bytes;
		byte buff[] = new byte[1000];

		try {
			while ((bytes = in.read(buff)) != -1) {
				out.write(buff, 0, bytes);
			}
		} catch (IOException e) {
			LOG.error("Error reading from stream", e);
		}

	}

	/**
	 * 
	 */
	private void configure() {
		port = 22;
		username = System.getProperty("user.name");
		host = config.getHost();

		if (config.hasPort()) {
			port = config.getPort();
		}

		if (config.hasUsername()) {
			username = config.getUsername();
		}

		if (config.hasPassword()) {
			password = config.getPassword();
		}
	}
}
