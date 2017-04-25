package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 06/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundPaymentHistoryResponse implements Serializable {

    private RefundPaymentDescription parent;
    private List<RefundSubPaymentDescription> sub_payments;

    public RefundPaymentDescription getParent() {
        return parent;
    }

    public void setParent(RefundPaymentDescription parent) {
        this.parent = parent;
    }

    public List<RefundSubPaymentDescription> getSub_payments() {
        return sub_payments;
    }

    public void setSub_payments(List<RefundSubPaymentDescription> sub_payments) {
        this.sub_payments = sub_payments;
    }
}
