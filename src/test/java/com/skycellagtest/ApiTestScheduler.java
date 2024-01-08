package com.skycellagtest;

import io.cucumber.core.cli.Main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : user
 * @created : 8.01.2024,21:21
 * @Email :aliyeidiris@gmail.com
 **/
public class ApiTestScheduler {
    public static void main(String[] args) {

        ScheduledExecutorService executorService= Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(()->{
            runCucumberTests();
            System.out.println("Scheduled tests are running...");
        },0,10, TimeUnit.MINUTES);
    }
    private static void runCucumberTests(){
        String [] argv={
                "--glue","com.skycellagtest.stepdefinitions",
                "--plugin", "json:target/cucumber-report.json",
                "classpath:Features/test4.feature"

        };
        Main.run(argv,Thread.currentThread().getContextClassLoader());
    }
}
