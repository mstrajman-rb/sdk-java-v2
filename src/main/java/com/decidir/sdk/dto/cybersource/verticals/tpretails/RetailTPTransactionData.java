package com.decidir.sdk.dto.cybersource.verticals.tpretails;

import com.decidir.sdk.dto.cybersource.Item;
import com.decidir.sdk.dto.cybersource.ShippingData;

import java.io.Serializable;
import java.util.List;

public class RetailTPTransactionData implements Serializable {

    private String account_id;
    private String account_name;
    private String account_category;
    private String account_antiquity;
    private String account_type;
    private String token_tp;
    private ShippingData ship_to;
    private String days_to_delivery;
    private Boolean tax_voucher_required;
    private String customer_loyality_number;
    private String coupon_code;
    private List<Item> items;

    public ShippingData getShip_to() {
        return ship_to;
    }

    public void setShip_to(ShippingData ship_to) {
        this.ship_to = ship_to;
    }

    public String getDays_to_delivery() {
        return days_to_delivery;
    }

    public void setDays_to_delivery(String days_to_delivery) {
        this.days_to_delivery = days_to_delivery;
    }

    public Boolean getTax_voucher_required() {
        return tax_voucher_required;
    }

    public void setTax_voucher_required(Boolean tax_voucher_required) { this.tax_voucher_required = tax_voucher_required; }

    public String getCustomer_loyality_number() {
        return customer_loyality_number;
    }

    public void setCustomer_loyality_number(String customer_loyality_number) { this.customer_loyality_number = customer_loyality_number; }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getAccount_id() { return account_id; }

    public void setAccount_id(String account_id) { this.account_id = account_id; }

    public String getAccount_name() { return account_name; }

    public void setAccount_name(String account_name) { this.account_name = account_name; }

    public String getAccount_category() { return account_category; }

    public void setAccount_category(String account_category) { this.account_category = account_category; }

    public String getAccount_antiquity() { return account_antiquity; }

    public void setAccount_antiquity(String account_antiquity) { this.account_antiquity = account_antiquity; }

    public String getAccount_type() { return account_type; }

    public void setAccount_type(String account_type) { this.account_type = account_type; }

    public String getToken_tp() { return token_tp; }

    public void setToken_tp(String token_tp) { this.token_tp = token_tp; }
}
