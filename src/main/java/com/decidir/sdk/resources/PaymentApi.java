package com.decidir.sdk.resources;

import com.decidir.sdk.dto.*;

import com.decidir.sdk.payments.GDSPaymentPciCardRequest;
import com.decidir.sdk.payments.GDSPaymentResponse;
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
  Call<PaymentResponse> payNoPci(@Body PaymentRequest payment);
  
  @POST("payments")
  Call<PaymentResponse> payPciCard(@Body PaymentPciCardRequest payment);
  
  @POST("payments")
  Call<PaymentResponse> payPciToken(@Body PaymentPciTokenRequest payment);

  @POST("payments")
  Call<OfflinePaymentResponse> payOffline(@Body OfflinePaymentRequest offlinePayment);

  @POST("payments")
  Call<GDSPaymentResponse> payGdsNoPci(@Body GDSPaymentRequest gdsPayment);

  @POST("payments")
  Call<GDSPaymentResponse> payGdsPciCard(GDSPaymentPciCardRequest gdsPayment);

  @PUT("payments/{paymentId}")
  Call<PaymentResponse> paymentConfirm(@Header("User") String user, @Path("paymentId") Long id, @Body ConfirmPaymentAmount confirmPayment);

}
