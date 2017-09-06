package com.decidir.sdk.dto;

public class BSAPaymentRequestNoPCI extends PaymentRequest{
    public BSAPaymentRequestNoPCI() {
        this.setPayment_mode("bsa");
    }
}
