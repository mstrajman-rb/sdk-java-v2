package com.decidir.sdk.dto.refunds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 06/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundPaymentDescription implements Serializable {

    private List<RefundDescription> history;

    public List<RefundDescription> getHistory() {
        return history;
    }

    public void setHistory(List<RefundDescription> history) {
        this.history = history;
    }
}
