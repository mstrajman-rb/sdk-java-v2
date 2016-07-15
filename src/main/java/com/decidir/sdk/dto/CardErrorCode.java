package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by biandra on 13/07/16.
 */
public enum CardErrorCode {

    INVALID_NUMBER("invalid_number"),
    INVALID_EXPIRY_MONTH("invalid_expiry_month"),
    INVALID_EXPIRY_YEAR("invalid_expiry_year"),
    INVALID_SECURITY_CODE("invalid_security_code"),
    INCORRECT_NUMBER("incorrect_number"),
    EXPIRED_CARD("expired_card"),
    INCORRECT_SECURITY_CODE("incorrect_security_code"),
    CARD_DECLINED("card_declined"),
    MISSING("missing"),
    PROCESSING_ERROR("processing_error");

    private String cardErrorCodeId;

    private CardErrorCode(final String cardErrorCodeId) {
        this.cardErrorCodeId = cardErrorCodeId;
    }

    @JsonValue
    public String getCardErrorCodeId() {
        return cardErrorCodeId;
    }
}
