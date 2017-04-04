package com.decidir.sdk.resources;

import com.decidir.sdk.dto.ConfirmPaymentAmount;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.PaymentNoPciRequest;
import com.decidir.sdk.dto.PaymentPciCardRequest;
import com.decidir.sdk.dto.PaymentPciTokenRequest;
import com.decidir.sdk.dto.PaymentResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface PaymentApi {

  @GET("payments")
  Call<Page> getPayments(@Query("offset") Integer offset,
                         @Query("pageSize") Integer pageSize,
                         @Query("siteOperationId") String siteOperationId,
                         @Query("siteId") String merchantId);

  @GET("payments/{paymentId}")
  Call<PaymentResponse> getPayment(@Path("paymentId") Long id);

  @POST("payments")
  Call<PaymentResponse> payNoPci(@Body PaymentNoPciRequest payment);
  
  @POST("payments")
  Call<PaymentResponse> payPciCard(@Body PaymentPciCardRequest payment);
  
  @POST("payments")
  Call<PaymentResponse> payPciToken(@Body PaymentPciTokenRequest payment);
  
  @PUT("payments/{paymentId}")
  Call<PaymentResponse> paymentConfirm(@Header("User") String user, @Path("paymentId") Long id, @Body ConfirmPaymentAmount confirmPayment);
}
