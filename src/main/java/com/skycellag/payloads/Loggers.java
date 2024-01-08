package com.skycellag.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 7.01.2024,16:53
 * @Email :aliyeidiris@gmail.com
 **/
public class Loggers {
    @JsonProperty
    private String loggerNumber;
    @JsonProperty
    private String loggerType;

    public Loggers(String loggerNumber, String loggerType) {
        this.loggerNumber = loggerNumber;
        this.loggerType = loggerType;
    }

    public String getLoggerNumber() {
        return loggerNumber;
    }

    public String getLoggerType() {
        return loggerType;
    }
}
