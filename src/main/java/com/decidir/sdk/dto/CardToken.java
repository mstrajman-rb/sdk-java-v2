package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by biandra on 22/09/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardToken implements Serializable {

    private String token;
    private Card card_brand;
    private String bin;
    private String last_four_digits;
    private String expiration_month;
    private String expiration_year;
    private Boolean expired;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Card getCard_brand() {
        return card_brand;
    }

    public void setCard_brand(Card card_brand) {
        this.card_brand = card_brand;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLast_four_digits() {
        return last_four_digits;
    }

    public void setLast_four_digits(String last_four_digits) {
        this.last_four_digits = last_four_digits;
    }

    public String getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(String expiration_month) {
        this.expiration_month = expiration_month;
    }

    public String getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(String expiration_year) {
        this.expiration_year = expiration_year;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
