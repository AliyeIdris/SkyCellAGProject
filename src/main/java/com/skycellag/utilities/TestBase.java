package com.skycellag.utilities;

import static com.skycellag.utilities.ConfigUtility.readConfig;

/**
 * @author : user
 * @created : 3.01.2024,17:44
 * @Email :aliyeidiris@gmail.com
 **/
public class TestBase {
    public String apiKey=readConfig("apiKey");
    public String username=readConfig("username");
    public String password=readConfig("password");

}
