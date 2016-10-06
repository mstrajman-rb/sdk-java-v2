package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 22/09/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundPaymentResponse implements Serializable {
    
    private Long id;
    private Long amount;
    private List<RefundSubPaymentResponse> sub_payments;
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

    public List<RefundSubPaymentResponse> getSub_payments() {
        return sub_payments;
    }

    public void setSub_payments(List<RefundSubPaymentResponse> sub_payments) {
        this.sub_payments = sub_payments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
