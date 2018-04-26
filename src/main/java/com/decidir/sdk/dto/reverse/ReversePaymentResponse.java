package com.decidir.sdk.dto.reverse;

import com.decidir.sdk.dto.CardError;
import com.decidir.sdk.dto.Status;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavo on 4/18/18.
 */
public class ReversePaymentResponse implements Serializable {

    private Long amount;
    private Boolean reversed_in_brand;
    private CardError error;
    private List<ReverseSubPaymentResponse> sub_payments;
    private Status status;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Boolean getReversed_in_brand() {
        return reversed_in_brand;
    }

    public void setReversed_in_brand(Boolean reversed_in_brand) {
        this.reversed_in_brand = reversed_in_brand;
    }

    public CardError getError() {
        return error;
    }

    public void setError(CardError error) {
        this.error = error;
    }

    public List<ReverseSubPaymentResponse> getSub_payments() {
        return sub_payments;
    }

    public void setSub_payments(List<ReverseSubPaymentResponse> sub_payments) {
        this.sub_payments = sub_payments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
