package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 21/06/16.
 */
public class PurchaseTotals implements Serializable{

    private String currency;
    private Double amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
