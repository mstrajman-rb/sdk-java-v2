package com.decidir.sdk.dto;

import java.io.Serializable;

public class SubPayment implements Serializable {

  private Long amount;
  private Integer installments;
  private String site_id;

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Integer getInstallments() {
    return installments;
  }

  public void setInstallments(Integer installments) {
    this.installments = installments;
  }

  public String getSite_id() {
    return site_id;
  }

  public void setSite_id(String site_id) {
    this.site_id = site_id;
  }
}
