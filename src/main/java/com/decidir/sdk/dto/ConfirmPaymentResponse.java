package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by biandra on 25/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmPaymentResponse implements Serializable {

    private Long id;
    private Long amount;
    private Long origin_amount;
    private String date;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getOrigin_amount() {
        return origin_amount;
    }

    public void setOrigin_amount(Long origin_amount) {
        this.origin_amount = origin_amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
