package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 21/06/16.
 */
public class CustomerInSite implements Serializable{

    private Long days_in_site;
    private Boolean is_guest;
    private String password;
    private Long num_of_transactions;
    private String cellphone_number;

    public Long getDays_in_site() {
        return days_in_site;
    }

    public void setDays_in_site(Long days_in_site) {
        this.days_in_site = days_in_site;
    }

    public Boolean getIs_guest() {
        return is_guest;
    }

    public void setIs_guest(Boolean is_guest) {
        this.is_guest = is_guest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getNum_of_transactions() {
        return num_of_transactions;
    }

    public void setNum_of_transactions(Long num_of_transactions) {
        this.num_of_transactions = num_of_transactions;
    }

    public String getCellphone_number() {
        return cellphone_number;
    }

    public void setCellphone_number(String cellphone_number) {
        this.cellphone_number = cellphone_number;
    }
}
