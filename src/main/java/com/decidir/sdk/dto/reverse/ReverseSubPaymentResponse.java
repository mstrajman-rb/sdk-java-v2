package com.decidir.sdk.dto.reverse;

import com.decidir.sdk.dto.CardError;
import com.decidir.sdk.dto.Status;

import java.io.Serializable;

/**
 * Created by gustavo on 4/18/18.
 */
public class ReverseSubPaymentResponse implements Serializable {

    private Long id;
    private String site_id;
    private Long amount;
    private Boolean reversed_in_brand;
    private CardError error;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Boolean getReversed_in_brand() {
        return reversed_in_brand;
    }

    public void setReversed_in_brand(Boolean reversed_in_brand) {
        this.reversed_in_brand = reversed_in_brand;
    }

    public CardError getError() {
        return error;
    }

    public void setError(CardError error) {
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
