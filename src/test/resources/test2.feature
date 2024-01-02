Feature: An authenticated user should be able to access his/her token

  Scenario: A user should be able to access her token and validate it
    Given user has a valid authentication information to connect to the Api
    When user sends a post request to the endpoint
    Then the api should return 200 status code
    And the api should return a response body with token detail
    And verify accessed token on "https://jwt.io/" website