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

    Scenario Outline: A user should NOT be able to create a logger with invalid loggerNumber or Invalid loggerType
      When user post request with invalid logger number "<loggerNumber>" or invalid logger type "<loggerType>"
      Then api should return 201 status code
      Examples:
      |loggerNumber         | loggerType|
      |123456               |MR_810T    |
      |123456               |MR-1111    |
      |12eef456gfbfg2345tr56|MR_812P    |
      |12eef456gfbfg2345tr56|AB-22T     |