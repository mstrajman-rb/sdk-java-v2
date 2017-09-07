package com.decidir.sdk.resources;

import com.decidir.sdk.dto.*;

import com.decidir.sdk.payments.GDSPaymentRequestPCI;
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
  Call<PaymentResponse> payPciCard(@Body PaymentPciRequest payment);
  
  @POST("payments")
  Call<PaymentResponse> payPciToken(@Body PaymentPciTokenRequest payment);

  @POST("payments")
  Call<OfflinePaymentResponse> payOffline(@Body OfflinePaymentRequest offlinePayment);

  @POST("payments")
  Call<GDSPaymentResponse> payGdsNoPci(@Body GDSPaymentRequestNoPCI gdsPayment);

  @POST("payments")
  Call<GDSPaymentResponse> payGdsPci(@Body GDSPaymentRequestPCI gdsPayment);

  @POST("payments")
  Call<BSAPaymentResponse> payBsaPci(@Body BSAPaymentRequestPCI bsaPaymentRequestPCI);

  @POST("payments")
  Call<BSAPaymentResponse> payBsaNoPci(@Body BSAPaymentRequestNoPCI bsaPaymentRequestNoPCI);

  @POST("payments")
  Call<AgroPaymentResponse> payAgroNoPci(@Body AgroPaymentRequestNoPCI agroPaymentRequestNoPCI);

  @PUT("payments/{paymentId}")
  Call<PaymentResponse> paymentConfirm(@Header("User") String user, @Path("paymentId") Long id, @Body ConfirmPaymentAmount confirmPayment);

}
