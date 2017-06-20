package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 22/09/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnulRefundResponse implements Serializable {
    
    private Long amount;
    private CardError status_details;
    private Status status;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CardError getStatus_details() {
        return status_details;
    }

    public void setStatus_details(CardError status_details) {
        this.status_details = status_details;
    }
}
