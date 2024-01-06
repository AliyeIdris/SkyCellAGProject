package com.skycellagtest.stepdefinitions;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.skycellag.payloads.sensorpayload.EndDeviceIds;
import com.skycellag.payloads.sensorpayload.MainPayload;
import com.skycellag.utilities.TestBase;
import com.skycellag.utilities.TestDataHolder;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;

import static com.skycellag.utilities.FileUtility.readConfig;
import static io.restassured.RestAssured.given;

/**
 * @author : user
 * @created : 6.01.2024,16:17
 * @Email :aliyeidiris@gmail.com
 **/
public class Test4Steps extends TestBase {
    Scenario scenario;
    String sensorURL = readConfig("sensorURL");
    String sensorReadURL = readConfig("sensorReadUR");
    Response response;
    String JwTToken;
    String loggerNumber;
    JsonObject ob;

    @Before
    public void beforeTestStart(Scenario scenario) {
        this.scenario = scenario;
    }

    //Background
    @Given("user has a valid JwT Token and logger number")
    public void userHasAValidJwTTokenAndLoggerNumber() {
        JwTToken= TestDataHolder.JwtToken();
        loggerNumber=TestDataHolder.loggerNumber();
    }

    //scenario 1
    @Given("user should have a valid url and request body")
    public void userShouldHaveAValidUrlAndRequestBody() {
        RestAssured.baseURI=sensorURL;
        String s= File.separator;
        String filePath=System.getProperty("user.dir")+s+"src"+s+"test"+s+"resources"+s+"TestData"+s+"sensorData.json";
        JsonObject body=(JsonObject) new JsonParser().parse(filePath);
        EndDeviceIds endDeviceIds=new EndDeviceIds("eui_"+loggerNumber,loggerNumber);
        ob=new JsonObject();
        ob.add("end_device_ids",new Gson().toJsonTree(endDeviceIds));
        ob.add("received_at",body.get("received_at"));
        ob.add("uplink_message",body.get("uplink_message"));
    }

    @When("user sends post request to the server")
    public void userSendsPostRequestToTheServer() {

        response=given().contentType(ContentType.JSON).and().headers("APIKEY",apiKey)
                .body(ob).when().post(sensorURL).then().extract().response();
    }

    //scenario 2
    @Given("user has a valid url and request body for temperature")
    public void userHasAValidUrlAndRequestBodyForTemperature() {
    }

    @When("user sends post request with temperature payload")
    public void userSendsPostRequestWithTemperaturePayload() {
    }

    @And("user verify the response body with temperature information")
    public void userVerifyTheResponseBodyWithTemperatureInformation() {
    }

    //scenario 3
    @Given("user has a valid url and request body for battery voltage")
    public void userHasAValidUrlAndRequestBodyForBatteryVoltage() {
    }

    @When("user sends post request with battery voltage payload")
    public void userSendsPostRequestWithBatteryVoltagePayload() {
    }

    @And("user verify the response body with battery voltage information")
    public void userVerifyTheResponseBodyWithBatteryVoltageInformation() {
    }

    //all scenario
    @Then("server should return {int} status code")
    public void serverShouldReturnStatusCode(int statusCode) {
    }
}


