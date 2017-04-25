package com.decidir.sdk.exceptions;

import com.decidir.sdk.dto.PaymentResponse;

/**
 * Created by biandra on 08/07/16.
 */
public class PaymentException extends RuntimeException{

    private int status;
    private String message;
    private PaymentResponse paymentResponse;

    public PaymentException(int status, String message, PaymentResponse paymentResponse){
        this.status = status;
        this.message = message;
        this.paymentResponse = paymentResponse;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentResponse getPayment() {
        return paymentResponse;
    }

    public void setPayment(PaymentResponse payment) {
        this.paymentResponse = payment;
    }
}
