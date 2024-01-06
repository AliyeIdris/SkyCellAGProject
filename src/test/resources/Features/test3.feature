@APITest
Feature: Admin user should be able to manage loggers

  @Test3
  Scenario Outline: An authorized user should be able to create a logger
    Given user has valid credentials and payload to create a logger "<LoggerType>"
    When user sends a post request to the api server
    Then api should return 201 status code
    And user verify the response content
    Examples:
    |LoggerType|
    |MR_810T   |
    |MR_812P   |