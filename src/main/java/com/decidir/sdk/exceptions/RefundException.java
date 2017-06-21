package com.decidir.sdk.exceptions;

import com.decidir.sdk.dto.RefundPaymentResponse;

/**
 * Created by biandra on 08/07/16.
 */
public class RefundException extends RuntimeException{

    private int status;
    private String message;
    private RefundPaymentResponse refundPaymentResponse;

    public RefundException(int status, String message, RefundPaymentResponse refundPaymentResponse){
        this.status = status;
        this.message = message;
        this.refundPaymentResponse = refundPaymentResponse;
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

    public RefundPaymentResponse getRefundPayment() {
        return refundPaymentResponse;
    }

    public void setRefundPayment(RefundPaymentResponse refundPaymentResponse) {
        this.refundPaymentResponse = refundPaymentResponse;
    }
}
