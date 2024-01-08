package com.skycellagtest.stepdefinitions;

import com.google.gson.Gson;
import com.skycellag.payloads.Loggers;
import com.skycellag.payloads.EndDeviceIds;
import com.skycellag.utilities.TestBase;
import com.skycellag.utilities.TestDataHolder;
import com.skycellagtest.ResponseHolder;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    String sensorReadURL = readConfig("sensorReadURL");
    Response response;
    String JwTToken;
    String loggerNumber;
    JSONObject bodyPayload;
    String fileAddress;
    JSONParser parser;
    Loggers loggers;

    @Before
    public void beforeTestStart(Scenario scenario) {
        this.scenario = scenario;
        parser = new JSONParser();
        String s = File.separator;
        fileAddress = System.getProperty("user.dir") + s + "src" + s + "test" + s + "resources" + s + "TestData" + s;
    }

    //Background
    @Given("user has a valid JwT Token and logger number")
    public void userHasAValidJwTTokenAndLoggerNumber() {
        JwTToken = ResponseHolder.JwtToken();
        loggerNumber = TestDataHolder.randomLoggerNumber();
        ResponseHolder.generateLogger(loggerNumber);
    }

    //Scenario 1: user sends sensor data to the server
    @Given("user should have a valid url and request body")
    public void userShouldHaveAValidUrlAndRequestBody() {
        RestAssured.baseURI = sensorURL;

        EndDeviceIds endDeviceIds = new EndDeviceIds("eui-" + loggerNumber, loggerNumber);
        try {
            Object obj = parser.parse(new FileReader(fileAddress + "sensorData.json"));
            bodyPayload = (JSONObject) obj;
            bodyPayload.put("end_device_ids", new Gson().toJsonTree(endDeviceIds));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @When("user sends post request to the server")
    public void userSendsPostRequestToTheServer() {
        response = given().contentType(ContentType.JSON).and().headers("APIKEY", apiKey)
                .body(bodyPayload.toString()).when().post(sensorURL).then().extract().response();
    }

    //scenario 2 and 3
    @Given("user has a valid url")
    public void userHasAValidUrl() {
        RestAssured.baseURI = sensorReadURL;
    }
    @And("user should already send sensor request to the server with valid loggerNumber")
    public void userShouldAlreadySendSensorRequestToTheServerWithValidLoggerNumber() {
        ResponseHolder.sendSensorData(loggerNumber);
        loggers = new Loggers(loggerNumber, "MR_810T");
    }
    @And("user has a valid request body for temperature")
    public void userHasAValidRequestBodyForTemperature() {
        try {
            Object obj = parser.parse(new FileReader(fileAddress + "temperature.json"));
            bodyPayload = (JSONObject) obj;
            bodyPayload.put("loggers", new Gson().toJsonTree(new ArrayList<>(Arrays.asList(loggers))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @When("user sends post request with temperature payload")
    public void userSendsPostRequestWithTemperaturePayload() {
        response = given().contentType(ContentType.JSON).and().headers("Authorization", "Bearer " + JwTToken)
                .body(bodyPayload.toString()).when().post(sensorReadURL).then().extract().response();
    }

    @And("user verify the response body with temperature information")
    public void userVerifyTheResponseBodyWithTemperatureInformation() {
        String responseBody = response.getBody().asPrettyString();
        Assert.assertTrue(responseBody.contains(loggerNumber));
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
        Assert.assertTrue(responseBody.contains(loggers.getLoggerType()));
        Assert.assertTrue(responseBody.contains("TEMPERATURE"));
        Assert.assertTrue(responseBody.contains("23.1"));
        scenario.log(responseBody);
    }

    //scenario 3
    @And("user has a valid request body for battery voltage")
    public void userHasAValidRequestBodyForBatteryVoltage() {
        try {
            Object obj = parser.parse(new FileReader(fileAddress + "batteryVoltage.json"));
            bodyPayload = (JSONObject) obj;
            bodyPayload.put("loggers", new Gson().toJsonTree(new ArrayList<>(Arrays.asList(loggers))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @When("user sends post request with battery voltage payload")
    public void userSendsPostRequestWithBatteryVoltagePayload() {
        response = given().contentType(ContentType.JSON).and().headers("Authorization", "Bearer " + JwTToken)
                .body(bodyPayload.toString()).when().post(sensorReadURL).then().extract().response();
    }

    @And("user verify the response body with battery voltage information")
    public void userVerifyTheResponseBodyWithBatteryVoltageInformation() {
        String responseBody = response.getBody().asPrettyString();
        Assert.assertTrue(responseBody.contains(loggerNumber));
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
        Assert.assertTrue(responseBody.contains(loggers.getLoggerType()));
        Assert.assertTrue(responseBody.contains("BATTERY_VOLTAGE"));
        Assert.assertTrue(responseBody.contains("1.3"));
        scenario.log(responseBody);
    }

    //all scenario
    @Then("server should return {int} status code")
    public void serverShouldReturnStatusCode(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());

    }
}


