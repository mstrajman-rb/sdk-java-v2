package com.decidir.sdk.converters;

import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.RefundPayment;
import retrofit2.Response;

/**
 * Created by biandra on 22/09/16.
 */
public class RefundConverter {

    public DecidirResponse<RefundPayment> convert(Response<RefundPayment> response, RefundPayment refundPayment) {
        DecidirResponse<RefundPayment> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(refundPayment);
        dResponse.setMessage(response.message());
        return dResponse;
    }
}
