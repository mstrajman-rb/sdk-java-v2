package com.decidir.sdk.dto.payments.bsa;

import com.decidir.sdk.dto.payments.PaymentRequest;

public class BSAPaymentRequestNoPCI extends PaymentRequest {
    public BSAPaymentRequestNoPCI() {
        this.setPayment_mode("bsa");
    }
}
