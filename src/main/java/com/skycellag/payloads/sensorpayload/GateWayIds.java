package com.skycellag.payloads.sensorpayload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 6.01.2024,16:40
 * @Email :aliyeidiris@gmail.com
 **/
public class GateWayIds {
    @JsonProperty("gateway_id")
    private String gatewayId;
    @JsonProperty("eui")
    private String eui;

    public GateWayIds(String gatewayId, String eui) {
        this.gatewayId = gatewayId;
        this.eui = eui;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public String getEui() {
        return eui;
    }
}
