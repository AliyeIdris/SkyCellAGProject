package com.skycellag.payloads.sensorpayload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author : user
 * @created : 6.01.2024,16:32
 * @Email :aliyeidiris@gmail.com
 **/
public class DecodedPayload {
    @JsonProperty
    private List<SensorData> sensorData;

    public DecodedPayload(List<SensorData> sensorData) {
        this.sensorData = sensorData;
    }

    public List<SensorData> getSensorData() {
        return sensorData;
    }
}
