@tag

Feature: Test Execution with zephyr
  I want to create the test cycle in zephyr and execute the test case after test method get passed

  @tag1
  Scenario: Execute test case one in zephyr with rest assure API
    Given I given valid authentication token
    When I Execute the test Method for automate first test method passed as we expected
    And I execute the test result as a pass with test key "AT-T1" when test method works as we expected
    Then I get the API response test case one staus details

 @tag1
  Scenario: Execute test case Two in zephyr with rest assure API
    Given I given valid authentication token
    When I Execute the test Method for automate second test case get blocked as we expected
    And I execute the test result as a Blocked with test key "AT-T2" when test method works as we expected
    Then I get the API response test case two staus details

    @tag1
  Scenario: Execute test case Three in zephyr with rest assure API
    Given I given valid authentication token
    When I Execute the test Method to automate third test case get failed as we expected
    And I execute the test result as a failed with test key "AT-T3" when test method works as we expected
    Then I get the API response test case three staus details