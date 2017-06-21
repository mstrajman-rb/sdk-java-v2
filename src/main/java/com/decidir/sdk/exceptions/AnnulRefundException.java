package com.decidir.sdk.exceptions;

import com.decidir.sdk.dto.AnnulRefundResponse;

/**
 * Created by biandra on 08/07/16.
 */
public class AnnulRefundException extends RuntimeException{

    private int status;
    private String message;
    private AnnulRefundResponse annulRefundResponse;

    public AnnulRefundException(int status, String message, AnnulRefundResponse annulRefundResponse){
        this.status = status;
        this.message = message;
        this.annulRefundResponse = annulRefundResponse;
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

    public AnnulRefundResponse getAnnulRefund() {
        return annulRefundResponse;
    }

    public void setAnnulRefund(AnnulRefundResponse annulRefundResponse) {
        this.annulRefundResponse = annulRefundResponse;
    }
}
