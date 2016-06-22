package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by biandra on 21/06/16.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Channel {

    WEB("Web");

    private final String  channelId;

    Channel (String channelId) {

        this.channelId = channelId;
    }

    @JsonCreator
    public static Channel forValue(String value) {

        return Channel.WEB;
    }

    @JsonValue
    public String toValue() {

        return Channel.WEB.name();
    }
}
