package com.decidir.sdk.resources;

import retrofit2.Call;
import retrofit2.http.*;

import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;

public interface PaymentApi {

  @GET("payments")
  Call<Page> getPayments(@Query("oOffset") int offset, @Query("oPageSize") int pageSize);

  @GET("payments/{paymentId}")
  Call<Payment> getPayment(@Path("paymentId") int id);

  @POST("payments/{paymentId}/refunds")
  Call<Payment> refundPayment(@Path("paymentId") int id);

  @POST("payments")
  Call<Payment> confirmPayment(@Body Payment payment);
}
