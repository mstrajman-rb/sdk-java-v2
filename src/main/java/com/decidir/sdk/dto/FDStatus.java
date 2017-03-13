package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Fraud detection result status
 * Created by biandra on 28/06/16.
 */
public class FDStatus implements Serializable {

    private String decision;
    private String reason_code;
    private String description;
    private ErrorType details;

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getReason_code() {
        return reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ErrorType getDetails() {
        return details;
    }

    public void setDetails(ErrorType details) {
        this.details = details;
    }
}
