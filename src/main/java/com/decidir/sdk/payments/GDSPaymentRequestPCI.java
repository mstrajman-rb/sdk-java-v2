package com.decidir.sdk.payments;

import com.decidir.sdk.dto.payments.pci.PaymentPciRequest;

public class GDSPaymentRequestPCI extends PaymentPciRequest {

    private String nro_location;
    private String iata_code;
    private String id_merchant;

    public GDSPaymentRequestPCI() {
        this.setPayment_mode("gds");
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

    public String getId_merchant() {
        return id_merchant;
    }

    public void setId_merchant(String id_merchant) {
        this.id_merchant = id_merchant;
    }
}
