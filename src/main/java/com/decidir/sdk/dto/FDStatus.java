package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 28/06/16.
 */
public class FDStatus implements Serializable {

    private String decision;
    private String reason_code;
    private String description;

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
}
