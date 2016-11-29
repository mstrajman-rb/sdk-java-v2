package com.decidir.sdk.converters;

import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.ConfirmPaymentResponse;
import retrofit2.Response;

/**
 * Created by biandra on 25/11/16.
 */
public class PaymentConfirmConverter {

    public DecidirResponse<ConfirmPaymentResponse> convert(Response<ConfirmPaymentResponse> response, ConfirmPaymentResponse confirmPayment) {
        DecidirResponse<ConfirmPaymentResponse> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(confirmPayment);
        dResponse.setMessage(response.message());
        return dResponse;
    }
}
