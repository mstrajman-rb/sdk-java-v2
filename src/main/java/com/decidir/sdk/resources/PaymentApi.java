package com.decidir.sdk.resources;

import retrofit2.Call;
import retrofit2.http.*;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;

public interface PaymentApi {

  @GET("payments")
  Call<Page> getPayments(@Query("oOffset") Integer offset, @Query("oPageSize") Integer pageSize);

  @GET("payments/{paymentId}")
  Call<Payment> getPayment(@Path("paymentId") Long id);

  @POST("payments")
  Call<Payment> confirmPayment(@Body Payment payment);
}
