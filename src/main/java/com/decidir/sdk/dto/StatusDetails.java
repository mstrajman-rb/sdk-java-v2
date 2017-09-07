package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 07/09/17.
 */
public class StatusDetails implements Serializable {

    private String ticket;
    private String card_autorization_code;
    private String address_validation_code;
    private CardError error;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getCard_autorization_code() {
        return card_autorization_code;
    }

    public void setCard_autorization_code(String card_autorization_code) {
        this.card_autorization_code = card_autorization_code;
    }

    public String getAddress_validation_code() {
        return address_validation_code;
    }

    public void setAddress_validation_code(String address_validation_code) {
        this.address_validation_code = address_validation_code;
    }

    public CardError getError() {
        return error;
    }

    public void setError(CardError error) {
        this.error = error;
    }
}
