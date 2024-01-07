package com.skycellag.utilities;

import com.skycellag.payloads.LoggerPojo;
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
public class TestDataHolder{
    static Response response;
     public static String randomLoggerNumber(){
        Random r = new Random();
        int digitCount= 16;
        StringBuffer sb = new StringBuffer();
        while(sb.length() <= digitCount){
            sb.append(String.format("%08x", r.nextInt()));
        }
        return sb.toString().substring(0, digitCount);
    }
}
