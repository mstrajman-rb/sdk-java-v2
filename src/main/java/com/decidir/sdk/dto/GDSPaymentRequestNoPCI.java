package com.decidir.sdk.dto;

import com.decidir.sdk.payments.GDSPaymentResponse;

public class GDSPaymentRequestNoPCI extends PaymentRequest{
    private String id_merchant;
    private String nro_location;
    private String iata_code;

    public GDSPaymentRequestNoPCI() {
        this.setPayment_mode("gds");
    }

    public String getId_merchant() {
        return id_merchant;
    }

    public void setId_merchant(String id_merchant) {
        this.id_merchant = id_merchant;
    }

    public String getNro_location() {
        return nro_location;
    }

    public void setNro_location(String nro_location) {
        this.nro_location = nro_location;
    }

    public String getIata_code() {
        return iata_code;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }
}
