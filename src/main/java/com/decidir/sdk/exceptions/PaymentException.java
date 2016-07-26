package com.decidir.sdk.exceptions;

import com.decidir.sdk.dto.Payment;

/**
 * Created by biandra on 08/07/16.
 */
public class PaymentException extends RuntimeException{

    private int status;
    private String message;
    private Payment payment;

    public PaymentException(int status, String message, Payment payment){
        this.status = status;
        this.message = message;
        this.payment = payment;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
