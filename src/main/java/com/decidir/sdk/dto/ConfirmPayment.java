package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by biandra on 25/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmPayment implements Serializable {

    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
