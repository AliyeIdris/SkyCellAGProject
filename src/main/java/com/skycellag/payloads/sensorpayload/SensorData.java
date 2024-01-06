package com.skycellag.payloads.sensorpayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skycellag.payloads.sensorpayload.DataPoints;

import java.util.List;

/**
 * @author : user
 * @created : 6.01.2024,16:39
 * @Email :aliyeidiris@gmail.com
 **/
public class SensorData {
    @JsonProperty
    private int amount;
    @JsonProperty
    private List<DataPoints> dataPoints;
    @JsonProperty
    private int isLogged;
    @JsonProperty
    private int type;
    @JsonProperty
    private String typeText;

    public SensorData(int amount, List<DataPoints> dataPoints, int isLogged, int type, String typeText) {
        this.amount = amount;
        this.dataPoints = dataPoints;
        this.isLogged = isLogged;
        this.type = type;
        this.typeText = typeText;
    }

    public int getAmount() {
        return amount;
    }

    public List<DataPoints> getDataPoints() {
        return dataPoints;
    }

    public int getIsLogged() {
        return isLogged;
    }

    public int getType() {
        return type;
    }

    public String getTypeText() {
        return typeText;
    }
}
