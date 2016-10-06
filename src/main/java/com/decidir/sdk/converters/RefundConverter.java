package com.decidir.sdk.converters;

import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.RefundPaymentHistoryResponse;
import com.decidir.sdk.dto.RefundPaymentResponse;
import retrofit2.Response;

/**
 * Created by biandra on 22/09/16.
 */
public class RefundConverter {

    public DecidirResponse<RefundPaymentResponse> convert(Response<RefundPaymentResponse> response, RefundPaymentResponse refundPayment) {
        DecidirResponse<RefundPaymentResponse> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(refundPayment);
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public DecidirResponse<RefundPaymentHistoryResponse> convert(Response<RefundPaymentHistoryResponse> response,
                                                                 RefundPaymentHistoryResponse refundPaymentHistory) {
        DecidirResponse<RefundPaymentHistoryResponse> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(refundPaymentHistory);
        dResponse.setMessage(response.message());
        return dResponse;
    }
}
