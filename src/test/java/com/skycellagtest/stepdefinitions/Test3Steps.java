package com.skycellagtest.stepdefinitions;

import com.skycellag.payloads.LoggerPayload;
import com.skycellag.utilities.TestBase;
import com.skycellag.utilities.TestDataHolder;
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
    String loggerURL=readConfig("loggerURL");
    Response response;
    LoggerPayload payload;
    @Given("user has valid credentials and payload to create a logger {string}")
    public void userHasValidCredentialsAndPayloadToCreateALogger(String loggerType) {
        RestAssured.baseURI=loggerURL;
        String loggerNumber= TestDataHolder.randomLoggerNumber();
        payload=new LoggerPayload(loggerNumber,loggerType,600);
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
        System.out.println("loggerNumber: "+payload.getLoggerNumber());
        System.out.println("loggerType: "+payload.getLoggerType());
        System.out.println("baseInterval: "+payload.getBaseInterval());

    }
}
