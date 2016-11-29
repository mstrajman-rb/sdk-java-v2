package com.decidir.sdk.resources;

import retrofit2.Call;
import retrofit2.http.*;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;

public interface PaymentApi {

  @GET("payments")
  Call<Page> getPayments(@Query("offset") Integer offset,
                         @Query("pageSize") Integer pageSize,
                         @Query("siteOperationId") String siteOperationId,
                         @Query("merchantId") String merchantId);

  @GET("payments/{paymentId}")
  Call<Payment> getPayment(@Path("paymentId") Long id);

  @POST("payments")
  Call<Payment> pay(@Body Payment payment);
}
