package com.decidir.sdk.dto;

/**
 * Created by ezequiel on 21/6/16.
 */
public enum Currency {

    ARS("ars");

    private final String  currencyId;

    Currency(String currencyId) {

        this.currencyId = currencyId;
    }
}
