package com.decidir.sdk.resources;

import com.decidir.sdk.dto.RefundPayment;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by biandra on 22/09/16.
 */
public interface RefundApi {

    @POST("payments/{paymentId}/refunds")
    Call<RefundPayment> refundPayment(@Path("paymentId") Long id);

    @DELETE("payments/{chargeId}/refunds/{refundId}")
    Call<RefundPayment> cancelRefund(@Path("chargeId") Long paymentId, @Path("paymentId") Long refundId);

}
