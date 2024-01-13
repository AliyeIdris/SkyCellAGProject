package com.skycellag.utilities;

import static com.skycellag.utilities.FileUtility.readConfig;

/**
 * @author : user
 * @created : 3.01.2024,17:44
 * @Email :aliyeidiris@gmail.com
 **/
public class TestBase {
    public static final String apiKey=readConfig("apiKey");
    public static final String username=readConfig("username");
    public static final String password=readConfig("password");

}
