package com.decidir.sdk.dto;

import java.io.Serializable;
import java.util.List;

public final class Page implements Serializable {

  private Integer total;

  private Integer limit;

  private Integer offset;

  private List<Payment> results;

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public List<Payment> getResults() {
    return results;
  }

  public void setResults(List<Payment> results) {
    this.results = results;
  }
}
