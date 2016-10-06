package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 05/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundPayment implements Serializable {

    private Long amount;
    private List<RefundSubPayment> sub_payments;

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
}
