package com.decidir.sdk.dto.confirms;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the payment confirmation body request
 * Could be empty 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmPaymentAmount implements Serializable {

    private Long amount;

    public ConfirmPaymentAmount() {
		this(null);
	}
    
    public ConfirmPaymentAmount(Long amount) {
    	this.amount = amount;
    }
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
