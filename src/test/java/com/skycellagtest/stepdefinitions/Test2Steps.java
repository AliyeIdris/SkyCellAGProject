package com.skycellagtest.stepdefinitions;

import com.skycellag.utilities.TestBase;
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

import java.util.Base64;


import static com.skycellag.utilities.FileUtility.readConfig;
import static io.restassured.RestAssured.given;

/**
 * @author : user
 * @created : 2.01.2024,17:48
 * @Email :aliyeidiris@gmail.com
 **/
public class Test2Steps extends TestBase {
    Scenario scenario;
    String tokenURL=readConfig("tokenURL");
    Response response;
    @Before
    public void beforeTest(Scenario scenario){
        this.scenario=scenario;
    }
    @Given("user has a valid authentication information to connect to the Api")
    public void userHasAValidAuthenticationInformationToConnectToTheApi() {
        RestAssured.baseURI=tokenURL;
    }

    @When("user sends a post request to the endpoint")
    public void userSendsAPostRequestToTheEndpoint() {
        response=given().contentType(ContentType.URLENC).accept("application/x-www-form-urlencoded")
                .formParam("client_id","webapp")
                .formParam("grant_type","password")
                .formParam("username",username)
                .formParam("password",password)
                .when().post(tokenURL).then().extract().response();

    }

    @Then("the api should return {int} status code")
    public void theApiShouldReturnStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @And("the api should return a response body with token detail")
    public void theApiShouldReturnAResponseBodyWithTokenDetail() {
        scenario.log(response.getBody().asPrettyString());
    }

    @And("verify the signature")
    public void verifyTheSignature() {
        String token=response.jsonPath().getString("access_token");
        String[] chunks=token.split("\\.");
        Base64.Decoder decoder=Base64.getUrlDecoder();
        String header=new String(decoder.decode(chunks[0]));
        String payload=new String(decoder.decode(chunks[1]));
        String signature=new String(decoder.decode(chunks[2]));
        scenario.log("Header: "+header);
        scenario.log("Payload: "+payload);
        scenario.log("Signature: "+signature);

    }
}
