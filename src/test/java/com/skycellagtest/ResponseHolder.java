package com.skycellagtest;

import com.google.gson.Gson;
import com.skycellag.payloads.LoggerPojo;
import com.skycellag.payloads.sensorpayload.EndDeviceIds;
import com.skycellag.utilities.TestBase;
import com.skycellag.utilities.TestDataHolder;
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

import static com.skycellag.utilities.FileUtility.readConfig;
import static io.restassured.RestAssured.given;

/**
 * @author : user
 * @created : 6.01.2024,17:20
 * @Email :aliyeidiris@gmail.com
 **/
public class ResponseHolder extends TestBase {
    private static String JwTToken;
    private static String loggerNumber;
    static Response response;

    public static String getJwTToken() {
        return JwTToken;
    }

    public static void setJwTToken(String jwTToken) {
        JwTToken = jwTToken;
    }

    public static String getLoggerNumber() {
        return loggerNumber;
    }

    public static void setLoggerNumber(String loggerNumber) {
        ResponseHolder.loggerNumber = loggerNumber;
    }
    public static String JwtToken(){
        String tokenURL=readConfig("tokenURL");
        RestAssured.baseURI=tokenURL;
        response=given().contentType(ContentType.URLENC).accept("application/x-www-form-urlencoded")
                .formParam("client_id","webapp")
                .formParam("grant_type","password")
                .formParam("username",username)
                .formParam("password",password)
                .when().post(tokenURL).then().extract().response();
        Assert.assertEquals(200,response.getStatusCode());
        String token=response.jsonPath().getString("access_token");
        return token;
    }
    public static void generateLogger(String loggerNumber){
        String loggerURL=readConfig("loggerURL");
        RestAssured.baseURI=loggerURL;
        LoggerPojo payload=new LoggerPojo(loggerNumber,"MR_810T",600);
        response=given().contentType(ContentType.JSON).and().headers("APIKEY",apiKey)
                .body(payload).when().post(loggerURL).then().extract().response();
        Assert.assertEquals(201,response.getStatusCode());
    }
    public static void sendSensorData(String loggerNumber){
        String sensorURL=readConfig("sensorURL");
        String s= File.separator;
        String fileAddress=System.getProperty("user.dir")+s+"src"+s+"test"+s+"resources"+s+"TestData"+s;
        RestAssured.baseURI=sensorURL;

        EndDeviceIds endDeviceIds=new EndDeviceIds("eui-"+loggerNumber,loggerNumber);
        JSONParser parser=new JSONParser();
        try {
            Object obj=parser.parse(new FileReader(fileAddress+"sensorData.json"));
            JSONObject bodyPayload =(JSONObject)obj;
            bodyPayload.put("end_device_ids",new Gson().toJsonTree(endDeviceIds));
            response=given().contentType(ContentType.JSON).and().headers("APIKEY",apiKey)
                    .body(bodyPayload.toString()).when().post(sensorURL).then().extract().response();
            Assert.assertEquals(201,response.getStatusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
