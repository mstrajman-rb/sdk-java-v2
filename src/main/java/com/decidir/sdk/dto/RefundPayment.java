package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 22/09/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundPayment implements Serializable {
    
    private Long id;
    private Long amount;
    private List<RefundSubPayment> sub_payments;
    private String status;

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

    public List<RefundSubPayment> getSub_payments() {
        return sub_payments;
    }

    public void setSub_payments(List<RefundSubPayment> sub_payments) {
        this.sub_payments = sub_payments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
