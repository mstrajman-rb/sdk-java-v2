package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by biandra on 12/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardError implements Serializable{

    private CardErrorCode error_type;
    private String message;
    private String code;
    private String actual_error;
    private String card_authorization_code;
    private String card_reason;

    public CardErrorCode getError_type() {
        return error_type;
    }

    public void setError_type(CardErrorCode error_type) {
        this.error_type = error_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActual_error() {
        return actual_error;
    }

    public void setActual_error(String actual_error) {
        this.actual_error = actual_error;
    }

    public String getCard_authorization_code() {
        return card_authorization_code;
    }

    public void setCard_authorization_code(String card_authorization_code) {
        this.card_authorization_code = card_authorization_code;
    }

    public String getCard_reason() {
        return card_reason;
    }

    public void setCard_reason(String card_reason) {
        this.card_reason = card_reason;
    }
}
