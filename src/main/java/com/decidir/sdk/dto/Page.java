package com.decidir.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Page implements Serializable {

  private Integer limit;
  private Integer offset;
  private ArrayList<Payment> results;
  private Boolean hasMore;

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

  public ArrayList<Payment> getResults() {
    return results;
  }

  public void setResults(ArrayList<Payment> results) {
    this.results = results;
  }

  public Boolean getHasMore() {
    return hasMore;
  }

  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
