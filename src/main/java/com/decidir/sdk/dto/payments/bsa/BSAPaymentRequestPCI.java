package com.decidir.sdk.dto.payments.bsa;

import com.decidir.sdk.dto.payments.Identification;
import com.decidir.sdk.dto.cybersource.FraudDetectionData;
import com.decidir.sdk.payments.Payment;

public class BSAPaymentRequestPCI extends Payment{
    String volatile_encrypted_data ;
    String public_request_key;
    String public_token;
    String issue_date;
    String flag_security_code;
    String flag_tokenization;
    String flag_selector_key;
    String flag_pei;
    String card_holder_name;
    Identification card_holder_identification;
    FraudDetectionData fraud_detection;
    BsaCardData bsa_card_data;

    public BSAPaymentRequestPCI() {
        this.setPayment_mode("bsa");
    }
}
