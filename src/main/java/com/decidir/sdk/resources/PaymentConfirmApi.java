package com.decidir.sdk.resources;

import com.decidir.sdk.dto.ConfirmPayment;
import com.decidir.sdk.dto.ConfirmPaymentResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by biandra on 25/11/16.
 */
public interface PaymentConfirmApi {

    @GET("payments/{paymentId}/confirmations")
    Call<ConfirmPaymentResponse> getPaymentConfirm(@Path("paymentId") Long id);

    @POST("payments/{paymentId}/confirmations")
    Call<ConfirmPaymentResponse> paymentConfirm(@Path("paymentId") Long id, @Body ConfirmPayment confirmPayment);
}
