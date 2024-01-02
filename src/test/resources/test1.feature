Feature: A customer should be able to track his/her asset fleet by using SkyCell’s LoRa-based loggers

  description: The customer has about 30'000 assets across 1'000 locations

  Scenario: customer should be able to replace the battery of the loggers
    Given customer should receive a notification of low battery level warning
    #below the number of location can be increased or decreased based on the requirement
    When customer selects the locations as "Location1" "Location2" "Location3" where the battery level will be changed
    And customer sets the battery level threshold as <30>
    Then LED light should blink as battery exchange happened