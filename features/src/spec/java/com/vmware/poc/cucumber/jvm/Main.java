package com.vmware.poc.cucumber.jvm;

public class Main {

	public static void main(String[] args) throws Throwable {
		String []options = new String[] {
			"--glue", "com.vmware.poc.cucumber.jvm.steps",
			"--format", "pretty",
			"--format", "html:cucumber-html-report",
			"--format", "json-pretty:cucumber-report.json",
			"classpath:features"
		};
		cucumber.cli.Main.main(options);
	}
}
