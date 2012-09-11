Feature: tc Server
	The purpose of this feature is to demonstrate the ability to startup/shutdown tc Server
	and verify that it is up and/or down when expected, along with running some other
	tools in the process.

	Scenario: Shutdown tc Server
		Given "tc Server" is running
		When "tc Server" is stopped
		And wait for 10 seconds
		Then "tc Server" should not be running

	Scenario: Startup tc Server
		Given "tc Server" is not running
		When "tc Server" is started
		And wait for 10 seconds
		Then "tc Server" should be running
		And the URL "http://172.16.227.138:8080" should be available
