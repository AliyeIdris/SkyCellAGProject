package com.skycellagtest.stepdefinitions;

import com.skycellag.payloads.LoggerPojo;
import com.skycellag.utilities.TestBase;
import com.skycellag.utilities.TestDataHolder;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.skycellag.utilities.FileUtility.readConfig;
import static io.restassured.RestAssured.given;

/**
 * @author : user
 * @created : 3.01.2024,17:39
 * @Email :aliyeidiris@gmail.com
 **/
public class Test3Steps extends TestBase {
    Scenario scenario;
    String loggerURL=readConfig("loggerURL");
    Response response;
    LoggerPojo payload;
    @Before
    public void beforeTest(Scenario scenario){
        this.scenario=scenario;
    }
    @Given("user has valid credentials and payload to create a logger {string}")
    public void userHasValidCredentialsAndPayloadToCreateALogger(String loggerType) {
        RestAssured.baseURI=loggerURL;
        String loggerNumber= TestDataHolder.randomLoggerNumber();
        payload=new LoggerPojo(loggerNumber,loggerType,600);
    }

    @When("user sends a post request to the api server")
    public void userSendsAPostRequestToTheApiServer() {
        response=given().contentType(ContentType.JSON).and().headers("APIKEY",apiKey)
                .body(payload).when().post(loggerURL).then().extract().response();
    }

    @Then("api should return {int} status code")
    public void apiShouldReturnStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @And("user verify the response content")
    public void userVerifyTheResponseContent() {
        scenario.log("loggerNumber: "+payload.getLoggerNumber());
        scenario.log("loggerType: "+payload.getLoggerType());
        scenario.log("baseInterval: "+payload.getBaseInterval());

    }

    @When("user post request with invalid logger number {string} or invalid logger type {string}")
    public void userPostRequestWithInvalidLoggerNumberOrInvalidLoggerType(String loggerNumber, String loggerType) {
        RestAssured.baseURI=loggerURL;
        payload=new LoggerPojo(loggerNumber,loggerType,600);
        response=given().contentType(ContentType.JSON).and().headers("APIKEY",apiKey)
                .body(payload).when().post(loggerURL).then().extract().response();

    }
}
