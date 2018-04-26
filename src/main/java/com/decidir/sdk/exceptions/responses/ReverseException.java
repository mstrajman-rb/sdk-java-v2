package com.decidir.sdk.exceptions.responses;

import com.decidir.sdk.dto.reverse.ReversePaymentResponse;

/**
 * Created by gustavo on 22/04/18.
 */
public class ReverseException extends RuntimeException implements ResponseException<ReversePaymentResponse>{

    private int status;
    private String message;
    private ReversePaymentResponse reversePaymentResponse;

    public ReverseException(int status, String message, ReversePaymentResponse reversePaymentResponse){
        this.status = status;
        this.message = message;
        this.reversePaymentResponse = reversePaymentResponse;
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


    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public ReversePaymentResponse getResponse() {
        return this.reversePaymentResponse;
    }
}
