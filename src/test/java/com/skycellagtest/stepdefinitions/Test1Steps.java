package com.skycellagtest.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author : user
 * @created : 2.01.2024,16:10
 * @Email :aliyeidiris@gmail.com
 **/
public class Test1Steps {
    @Given("customer should receive a notification of low battery level warning")
    public void customerShouldReceiveANotificationOfLowBatteryLevelWarning() {
    }

    @When("customer selects the locations as {string} {string} {string} where the battery level will be changed")
    public void customerSelectsTheLocationsAsWhereTheBatteryLevelWillBeChanged(String location1, String location2, String location3) {
    }

    @And("customer sets the battery level threshold as <{int}>")
    public void customerSetsTheBatteryLevelThresholdAs(int batteryLevel) {
    }

    @Then("LED light should blink as battery exchange happened")
    public void ledLightShouldBlinkAsBatteryExchangeHappened() {
    }
}
