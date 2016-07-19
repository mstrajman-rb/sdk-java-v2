package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by biandra on 13/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum CardErrorCode {

    INVALID_NUMBER("invalid_number"),
    INVALID_DATE_CARD("invalid_date_card"),
    EXPIRED_CARD("expired_card"),
    CARD_DECLINED("card_declined"),//puede q no vaya mas...
    PROCESSING_ERROR("processing_error"),
    INVALID_CARD("invalid_card"), //generic error
    REQUEST_AUTHORIZATION("request_authorization"),
    FINANCIAL_REASONS("financial_reasons"),
    CYBERSOURCE_ERROR("cybersource_error"),
    EMPTY("");//TODO: este no deberia pasar nunca!!

    private String cardErrorCodeId;

    private CardErrorCode(final String cardErrorCodeId) {
        this.cardErrorCodeId = cardErrorCodeId;
    }

    @JsonValue
    public String getCardErrorCodeId() {
        return cardErrorCodeId;
    }
}
