package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by biandra on 12/07/16.
 */
public enum Status {

    APPROVED("approved"),
    REJECTED("rejected"),
    REVIEW("review"),
    REVERSED("reversed"),
    CANCELLED("cancelled");

    private String statusId;

    private Status(final String statusId) {
        this.statusId = statusId;
    }

    @JsonValue
    public String getStatusId() {
        return statusId;
    }
}
