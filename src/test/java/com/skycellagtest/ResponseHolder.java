package com.skycellagtest;

/**
 * @author : user
 * @created : 6.01.2024,17:20
 * @Email :aliyeidiris@gmail.com
 **/
public class ResponseHolder {
    private static String JwTToken;
    private static String loggerNumber;

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
}
