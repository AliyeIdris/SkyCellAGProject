package com.skycellag.utilities;

import java.util.Random;

/**
 * @author : user
 * @created : 3.01.2024,17:54
 * @Email :aliyeidiris@gmail.com
 **/
public class TestDataHelper {
     public static String randomLoggerNumber(){
        Random r = new Random();
        int randomDigit= r.nextInt(16);
        StringBuffer sb = new StringBuffer();
        while(sb.length() < randomDigit){
            sb.append(String.format("%08x", r.nextInt()));
        }
        return sb.toString().substring(0, randomDigit);
    }

}
