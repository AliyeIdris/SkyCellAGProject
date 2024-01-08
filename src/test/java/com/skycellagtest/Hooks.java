package com.skycellagtest;

import com.skycellag.utilities.TestBase;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

/**
 * @author : user
 * @created : 2.01.2024,16:08
 * @Email :aliyeidiris@gmail.com
 **/
public class Hooks extends TestBase {

    @Before
    public void setUp(){
        RestAssured.authentication=RestAssured.basic(username,password);
    }
}
