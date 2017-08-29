package com.decidir.sdk.payments;

import com.decidir.sdk.dto.PaymentResponse;

import java.io.Serializable;

public class GDSPaymentResponse extends PaymentResponse implements Serializable{
    GDSPaymentResponse(){
        this.setPayment_mode("gds");
    }
}
