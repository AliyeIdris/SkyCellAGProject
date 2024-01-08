package com.skycellag.payloads.sensorpayload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 6.01.2024,16:28
 * @Email :aliyeidiris@gmail.com
 **/
public class MainPayload {
    @JsonProperty("end_device_ids")
    private EndDeviceIds endDeviceIds;
    @JsonProperty("received_at")
    private String receivedAt;
    @JsonProperty("uplink_message")
    private UplinkMessage uplinkMessage;

    public MainPayload(EndDeviceIds endDeviceIds, String receivedAt, UplinkMessage uplinkMessage) {
        this.endDeviceIds = endDeviceIds;
        this.receivedAt = receivedAt;
        this.uplinkMessage = uplinkMessage;
    }

    public EndDeviceIds getEndDeviceIds() {
        return endDeviceIds;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public UplinkMessage getUplinkMessage() {
        return uplinkMessage;
    }
}
