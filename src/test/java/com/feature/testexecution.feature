#@tag
Feature: Test Execution with zephyr
  I want to create the test cycle in zephyr and execute the test case after test method get passed

  @tag1
  Scenario: 'AT-T1' To check the login functionality with valid input and Execute the test case status in zephyr with API rest Assured
    Given I given valid authentication token
    And I Execute with selenium code test one to open the log in page and enter valid username "hphari2026@gmail.com" and password "Avengers@2023"
    And To check test case one and Click the login button it should navigate to home page

	@tag2
  Scenario: 'AT-T2' To check the login functionality with Invalid input and Execute the test case status in zephyr with API rest Assured
    Given I given valid authentication token
    And I Execute with selenium code test two to open the log in page and enter Invalid username and password
    And To check test case two and Click the login button it should navigate to home page
