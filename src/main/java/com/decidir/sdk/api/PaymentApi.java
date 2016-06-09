package com.decidir.sdk.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;

public interface PaymentApi {

  @GET("payments")
  Call<Page> payments();

  @GET("payments/{paymentId}")
  Call<Payment> getPayment(@Path("paymentId") int id);

  @DELETE("payments/{paymentId}")
  Call<Payment> deletePayment(@Path("paymentId") int id);

  @POST("payments")
  Call<Payment> confirmPayment(@Body Payment payment);
}
