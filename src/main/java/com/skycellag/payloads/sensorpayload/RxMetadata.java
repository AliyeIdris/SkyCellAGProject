package com.skycellag.payloads.sensorpayload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : user
 * @created : 6.01.2024,16:32
 * @Email :aliyeidiris@gmail.com
 **/
public class RxMetadata {
    @JsonProperty("gateway_ids")
    private GateWayIds gateWayIds;
    @JsonProperty("timestamp")
    private int timeStamp;
    @JsonProperty("rssi")
    private int rssi;
    @JsonProperty("channel_rssi")
    private int channelRssi;
    @JsonProperty("snr")
    private int snr;

    public RxMetadata(GateWayIds gateWayIds, int timeStamp, int rssi, int channelRssi, int snr) {
        this.gateWayIds = gateWayIds;
        this.timeStamp = timeStamp;
        this.rssi = rssi;
        this.channelRssi = channelRssi;
        this.snr = snr;
    }

    public GateWayIds getGateWayIds() {
        return gateWayIds;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public int getRssi() {
        return rssi;
    }

    public int getChannelRssi() {
        return channelRssi;
    }

    public int getSnr() {
        return snr;
    }
}
