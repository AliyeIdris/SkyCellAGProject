package com.skycellag.utilities;

import io.restassured.response.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

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
    private static int counter = 10;
    public static int incrementAndReturn() {
        counter++;
        return counter;
    }
    public static String receivedTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
    }
}
