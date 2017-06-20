package com.decidir.sdk.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 19/06/17.
 */
public class PaymentForm implements Serializable {

    private Currency currency;
    private Long amount;
    private PaymentType payment_type; // single / distributed
    private List<SubPayment> sub_payments;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public PaymentType getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(PaymentType payment_type) {
        this.payment_type = payment_type;
    }

    public List<SubPayment> getSub_payments() {
        return sub_payments;
    }

    public void setSub_payments(List<SubPayment> sub_payments) {
        this.sub_payments = sub_payments;
    }
}
