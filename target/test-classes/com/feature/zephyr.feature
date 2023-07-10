Feature: Create Test Plan in Zephyr

  Scenario: Create a new test plan
    Given I have a valid authentication token
    When I create a test plan with name My Automation Test Plan
    Then the test plan should be created successfully
 
 #@tag
 #Feature: Automate Zephyr test cases from jira
 #I want to automate the feasibility test cases from the project
#
 #@tag1
 #Scenario: Create the test cycles
 #Given I want to hit the Bacse URI and Authenticate token
 #When I create the testcycle in zephyr
 #Then I validate the result
  #
 #@execution
#Scenario: Execute the test cases in the test cycle
 #When I Want to execute test cases into created test cycle
 #And I invoke the input from the property file
 #Then I validate the executed results as expected
 