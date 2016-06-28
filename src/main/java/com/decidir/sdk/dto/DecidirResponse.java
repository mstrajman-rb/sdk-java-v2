package com.decidir.sdk.dto;

import retrofit2.Call;

import java.io.IOException;

/**
 * Created by ezequiel on 2/6/16.
 */
public class DecidirResponse<T> {

  private int status;

  private T result;

  private String message;


  public static DecidirResponse build(Call call) {

    try {

      retrofit2.Response response = call.execute();

      DecidirResponse dr = new DecidirResponse();

      if (response.isSuccessful()) {

        dr.setResult(response.body());
        dr.setStatus(response.code());
      } else {

        okhttp3.Response r = response.raw();
        dr.setStatus(r.code());
        dr.setMessage(r.message());
      }

      return dr;

    } catch(IOException ioe) {

      DecidirResponse dr = new DecidirResponse();

      dr.setStatus(500);
      dr.setMessage(ioe.getMessage());

      ioe.printStackTrace();

      return dr;
    }


  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
