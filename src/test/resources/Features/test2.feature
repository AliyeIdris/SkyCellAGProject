@APITest
Feature: An authenticated user should be able to access his/her token

  @Test2
  Scenario: A user should be able to access her token and validate it
    Given user has a valid authentication information to connect to the Api
    When user sends a post request to the endpoint
    Then the api should return 200 status code
    And the api should return a response body with token detail
    And verify the signature

    Scenario Outline:
    A user should NOT be able to access her token with incorrect credentials
      When user enter invalid username "<username>" or invalid password "<password>"
      Then api should return 400 status code
      And user should not be able to access his token
      Examples:
      |username               | password        |
      |qa_interview@skycell.ch|Qa_interview|
      |qa_intervie            |Qa_interview2023!|
      |efrhjrekjrk            |hjhewuı2432342   |
      |qa_interview@skycell.ch|                 |
      |                       |Qa_interview2023!|
      |                       |                 |
