package com.skycellag.payloads.sensorpayload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author : user
 * @created : 6.01.2024,16:29
 * @Email :aliyeidiris@gmail.com
 **/
public class UplinkMessage {
    @JsonProperty("decoded_payload")
    private DecodedPayload decodedPayload;
    @JsonProperty("rx_metadata")
    private List<RxMetadata> rxMetadata;

    public UplinkMessage(DecodedPayload decodedPayload, List<RxMetadata> rxMetadata) {
        this.decodedPayload = decodedPayload;
        this.rxMetadata = rxMetadata;
    }

    public DecodedPayload getDecodedPayload() {
        return decodedPayload;
    }

    public List<RxMetadata> getRxMetadata() {
        return rxMetadata;
    }
}
