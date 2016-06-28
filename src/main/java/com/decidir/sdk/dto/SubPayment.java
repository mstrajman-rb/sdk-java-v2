package com.decidir.sdk.dto;

import java.io.Serializable;

public class SubPayment implements Serializable {

  private int amount;

  private int installments;

  private int site_id;

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getInstallments() {
    return installments;
  }

  public void setInstallments(int installments) {
    this.installments = installments;
  }

  public int getSite_id() {
    return site_id;
  }

  public void setSite_id(int site_id) {
    this.site_id = site_id;
  }
}
