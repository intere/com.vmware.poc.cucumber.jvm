Feature: Tomcat
	The purpose of this feature is to demonstrate the ability to startup/shutdown tomcat
	and verify that it is up and/or down when expected, along with running some other
	tools in the process.

	Scenario: Shutdown Tomcat
		Given "Tomcat" is running
		When "Tomcat" is stopped
		And wait for 10 seconds
		Then "Tomcat" should not be running

	Scenario: Startup Tomcat
		Given "Tomcat" is not running
		When "Tomcat" is started
		And wait for 10 seconds
		Then "Tomcat" should be running
