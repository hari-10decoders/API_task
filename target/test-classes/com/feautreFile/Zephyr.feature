@tag
Feature: Automate Zephyr test cases	
  I want to automate the feasible possible test cases 

  @tag1
  Scenario: Create the test cycle
    Given I want to hit the BaseURI and authenticate the token
    When I create the testcycle in zephyr
    Then I validate the result

  @tag2
  Scenario : Execute the test case 
    When I Want to execute test cases into created test cycle
    And I invoke the input from the property file
    Then I validate the executed results as expected