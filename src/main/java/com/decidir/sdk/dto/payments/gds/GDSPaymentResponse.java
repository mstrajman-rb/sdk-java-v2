package com.decidir.sdk.dto.payments.gds;

import com.decidir.sdk.dto.payments.PaymentResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GDSPaymentResponse extends PaymentResponse implements Serializable{
    private String nro_location;
    private String iata_code;

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
