package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by biandra on 12/07/16.
 */
public enum Status {

    APPROVED,
    APPROVED_WITH_REFUNDED,
    REJECTED,
    REVIEW,
    REFUNDED,
    ANNULLED,
    ACCREDITED,
    UNDEFINED;

    private static Map<String, Status> statusMap = new HashMap<>(8);
    static {
        statusMap.put("approved", APPROVED);
        statusMap.put("approved_with_refunded", APPROVED_WITH_REFUNDED);
        statusMap.put("rejected", REJECTED);
        statusMap.put("review", REVIEW);
        statusMap.put("refunded", REFUNDED);
        statusMap.put("annulled", ANNULLED);
        statusMap.put("accredited", ACCREDITED);
        statusMap.put("undefined", UNDEFINED);
    }

    @JsonCreator
    public static Status forValue(String value) {
        Status status = statusMap.get(value.toLowerCase());
        return status != null ? status : UNDEFINED ;
    }


    @JsonValue
    public String toValue() {
        for (Map.Entry<String, Status> entry : statusMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null;
    }

}
