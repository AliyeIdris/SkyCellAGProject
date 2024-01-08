package com.skycellag.utilities;

import static com.skycellag.utilities.FileUtility.readConfig;

/**
 * @author : user
 * @created : 3.01.2024,17:44
 * @Email :aliyeidiris@gmail.com
 **/
public class TestBase {
    public static String apiKey=readConfig("apiKey");
    public static String username=readConfig("username");
    public static String password=readConfig("password");

}
