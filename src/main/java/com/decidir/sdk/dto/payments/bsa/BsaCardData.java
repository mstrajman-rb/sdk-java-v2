package com.decidir.sdk.dto.payments.bsa;

public class BsaCardData {
    private String private_token;
    private String aditionalcrypt;
    private String hexsum;
    private String counter;
    private String card_expiration;
    private String security_code;
    private String pan;

    public String getPrivate_token() {
        return private_token;
    }

    public void setPrivate_token(String private_token) {
        this.private_token = private_token;
    }

    public String getAditionalcrypt() {
        return aditionalcrypt;
    }

    public void setAditionalcrypt(String aditionalcrypt) {
        this.aditionalcrypt = aditionalcrypt;
    }

    public String getHexsum() {
        return hexsum;
    }

    public void setHexsum(String hexsum) {
        this.hexsum = hexsum;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getCard_expiration() {
        return card_expiration;
    }

    public void setCard_expiration(String card_expiration) {
        this.card_expiration = card_expiration;
    }

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
