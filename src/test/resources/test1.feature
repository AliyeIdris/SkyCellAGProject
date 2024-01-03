Feature: A customer should be able to track his/her asset fleet by using SkyCell’s LoRa-based loggers

  description: The customer has about 30'000 assets across 1'000 locations

  Scenario Outline: customer should be able to replace the battery of the loggers
    Given customer should receive a notification of low battery level warning
    When customer selects the locations where the battery level will be changed "<Locations>"
    And customer sets the battery level threshold as <BatteryLevel>
    Then LED light should blink as battery exchange happened
    Examples:
    |Locations  |BatteryLevel|
    |location-A |  30        |
    |location-B |  25        |
    |location-C |  20        |

  # As in the examples above, user can add as many as locations he want dynamically,
  # And set the battery level dynamically as well

