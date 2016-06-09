package com.decidir.sdk.dto;

import java.io.Serializable;

public class SubPayment implements Serializable {

  private int amount;

  private int installments;

  private int siteId;

  public int getAmount() {
    return amount;
  }

  public int getInstallments() {
    return installments;
  }

  public int getSiteId() {
    return siteId;
  }
}
