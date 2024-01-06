package com.skycellag.utilities;

import com.skycellag.payloads.LoggerPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Random;

import static com.skycellag.utilities.FileUtility.readConfig;
import static io.restassured.RestAssured.given;

/**
 * @author : user
 * @created : 3.01.2024,17:54
 * @Email :aliyeidiris@gmail.com
 **/
public class TestDataHolder extends TestBase{
    static Response response;
     public static String randomLoggerNumber(){
        Random r = new Random();
        int randomDigit= r.nextInt(16);
        StringBuffer sb = new StringBuffer();
        while(sb.length() < randomDigit){
            sb.append(String.format("%08x", r.nextInt()));
        }
        return sb.toString().substring(0, randomDigit);
    }
    public static String JwtToken(){
        String tokenURL=readConfig("tokenURL");
        RestAssured.baseURI=tokenURL;
        response=response=given().contentType(ContentType.URLENC).accept("application/x-www-form-urlencoded")
                .formParam("client_id","webapp")
                .formParam("grant_type","password")
                .formParam("username",username)
                .formParam("password",password)
                .when().post(tokenURL).then().extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        String token=response.jsonPath().getString("access_token");
        return token;
    }
    public static String loggerNumber(){
        String loggerURL=readConfig("loggerURL");
        RestAssured.baseURI=loggerURL;
        String loggerNumber=randomLoggerNumber();
        LoggerPayload payload=new LoggerPayload(loggerNumber,"MR_810T",600);
        response=given().contentType(ContentType.JSON).and().headers("APIKEY",apiKey)
                .body(payload).when().post(loggerURL).then().extract().response();
        Assert.assertEquals(response.getStatusCode(),201);
        return loggerNumber;
    }
}
