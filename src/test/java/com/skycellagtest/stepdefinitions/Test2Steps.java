package com.skycellagtest.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.skycellag.utilities.ConfigUtility.readConfig;
import static io.restassured.RestAssured.given;

/**
 * @author : user
 * @created : 2.01.2024,17:48
 * @Email :aliyeidiris@gmail.com
 **/
public class Test2Steps {
    String url=readConfig("tokenURL");
    String username=readConfig("username");
    String password=readConfig("password");
    Response response;
    @Given("user has a valid authentication information to connect to the Api")
    public void userHasAValidAuthenticationInformationToConnectToTheApi() {
        RestAssured.baseURI=url;
        RestAssured.authentication=RestAssured.basic(username,password);
    }

    @When("user sends a post request to the endpoint")
    public void userSendsAPostRequestToTheEndpoint() {
        response=given().contentType(ContentType.URLENC).accept("application/x-www-form-urlencoded")
                .formParam("client_id","webapp")
                .formParam("grant_type","password")
                .formParam("username",username)
                .formParam("password",password)
                .when().post(url).then().extract().response();

    }

    @Then("the api should return {int} status code")
    public void theApiShouldReturnStatusCode(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());
    }

    @And("the api should return a response body with token detail")
    public void theApiShouldReturnAResponseBodyWithTokenDetail() {
        response.prettyPrint();
    }

    @And("verify accessed token on {string} website")
    public void verifyAccessedTokenOnWebsite(String arg0) {
    }
}
