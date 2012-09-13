Feature: tc Server
	The purpose of this feature is to demonstrate the ability to startup/shutdown tc Server
	and verify that it is up and/or down when expected, along with running some other
	tools in the process.

	Scenario: Shutdown tc Server
		Given "tc Server" is running
		When "tc Server" is stopped
		Then "tc Server" should not be running

	Scenario: Startup tc Server
		Given "tc Server" is not running
		When "tc Server" is started
		Then "tc Server" should be running
		And the "home" page for "tc Server" should be "available"
