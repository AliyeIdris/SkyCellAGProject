@APITest @Test4
Feature: Admin user should be able to send request to api server and read data back from the server

  Background: User should have API specification and Logger Number for each scenario
    Given user has a valid JwT Token and logger number

  Scenario: User should be able to send request to the api server
    Given user should have a valid url and request body
    When user sends post request to the server
    Then server should return 201 status code

  Scenario: User should be able to read temperature information from the server
    Given user has a valid url
    And user should already send sensor request to the server with valid loggerNumber
    And user has a valid request body for temperature
    When user sends post request with temperature payload
    Then server should return 200 status code
    And user verify the response body with temperature information
    And api should return the dataValue as 23.1

  Scenario: User should be able to read battery voltage information from the server
    Given user has a valid url
    And user should already send sensor request to the server with valid loggerNumber
    And user has a valid request body for battery voltage
    When user sends post request with battery voltage payload
    Then server should return 200 status code
    And user verify the response body with battery voltage information
    And api should return the dataValue as 1.3
