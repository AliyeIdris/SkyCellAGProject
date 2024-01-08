package com.skycellag.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 6.01.2024,16:47
 * @Email :aliyeidiris@gmail.com
 **/
public class DataPoints {
    @JsonProperty
    private int index;
    @JsonProperty
    private float temperature;
    @JsonProperty
    private float voltage;

    public DataPoints(int index, float temperature, float voltage) {
        this.index = index;
        this.temperature = temperature;
        this.voltage = voltage;
    }

    public int getIndex() {
        return index;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getVoltage() {
        return voltage;
    }
}
