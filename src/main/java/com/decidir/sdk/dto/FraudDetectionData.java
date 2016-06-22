package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 21/06/16.
 */
public class FraudDetectionData implements Serializable {

    private BillingData bill_to;
    private PurchaseTotals purchase_totals;
    private String channel;
    private CustomerInSite customer_in_site;
    private String device_unique_id;
    private TicketingTransactionData ticketing_transaction_data;
    private String status;

    public BillingData getBill_to() {
        return bill_to;
    }

    public void setBill_to(BillingData bill_to) {
        this.bill_to = bill_to;
    }

    public PurchaseTotals getPurchase_totals() {
        return purchase_totals;
    }

    public void setPurchase_totals(PurchaseTotals purchase_totals) {
        this.purchase_totals = purchase_totals;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public CustomerInSite getCustomer_in_site() {
        return customer_in_site;
    }

    public void setCustomer_in_site(CustomerInSite customer_in_site) {
        this.customer_in_site = customer_in_site;
    }

    public String getDevice_unique_id() {
        return device_unique_id;
    }

    public void setDevice_unique_id(String device_unique_id) {
        this.device_unique_id = device_unique_id;
    }

    public TicketingTransactionData getTicketing_transaction_data() {
        return ticketing_transaction_data;
    }

    public void setTicketing_transaction_data(TicketingTransactionData ticketing_transaction_data) {
        this.ticketing_transaction_data = ticketing_transaction_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
