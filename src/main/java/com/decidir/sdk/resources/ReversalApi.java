package com.decidir.sdk.resources;

import com.decidir.sdk.dto.reverse.ReversePaymentResponse;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by gustavo on 4/18/18.
 */
public interface ReversalApi {

    @POST("payments/{paymentId}/reverse")
    Call<ReversePaymentResponse> refundPayment(@Header("User") String user, @Path("paymentId") Long paymentId);

}
