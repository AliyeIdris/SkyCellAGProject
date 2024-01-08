package com.skycellagtest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author : user
 * @created : 2.01.2024,16:07
 * @Email :aliyeidiris@gmail.com
 **/
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-testReport.html",
                  "junit:target/cucumber-results.xml"},
        features = {"classpath:Features/"},
        tags="@APITest"
)
public class CucumberTest {
}
