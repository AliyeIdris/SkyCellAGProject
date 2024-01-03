package com.skycellag.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 3.01.2024,17:27
 * @Email :aliyeidiris@gmail.com
 **/
public class LoggerPayload {
    @JsonProperty
    private String loggerNumber;
    @JsonProperty
    private String loggerType;
    @JsonProperty
    private int baseInterval;

    public LoggerPayload(String loggerNumber, String loggerType, int baseInterval) {
        this.loggerNumber = loggerNumber;
        this.loggerType = loggerType;
        this.baseInterval = baseInterval;
    }

    public String getLoggerNumber() {
        return loggerNumber;
    }

    public String getLoggerType() {
        return loggerType;
    }

    public int getBaseInterval() {
        return baseInterval;
    }
}
