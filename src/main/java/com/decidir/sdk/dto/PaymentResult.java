package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 07/07/16.
 */
public class PaymentResult implements Serializable{

    private Payment payment;
    private DecidirError errorDetail;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public DecidirError getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(DecidirError errorDetail) {
        this.errorDetail = errorDetail;
    }
}
