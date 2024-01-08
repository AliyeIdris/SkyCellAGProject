package com.skycellag.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 6.01.2024,16:28
 * @Email :aliyeidiris@gmail.com
 **/
public class EndDeviceIds {
    @JsonProperty("device_id")
    private String device_id;
    @JsonProperty("dev_eui")
    private String dev_eui;

    public EndDeviceIds(String deviceId, String dev_eui) {
        this.device_id = deviceId;
        this.dev_eui = dev_eui;
    }

    public String getDeviceId() {
        return device_id;
    }

    public String getDev_eui() {
        return dev_eui;
    }
}
