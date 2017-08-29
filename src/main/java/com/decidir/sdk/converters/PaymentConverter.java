package com.decidir.sdk.converters;

import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.OfflinePaymentResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.PaymentResponse;

import com.decidir.sdk.payments.GDSPaymentResponse;
import retrofit2.Response;

import java.io.Serializable;

/**
 * Created by biandra on 08/07/16.
 */
public class PaymentConverter {

    public <A> DecidirResponse<A>  convert(Response<A> response, A body) {
        DecidirResponse<A> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(body);
        dResponse.setMessage(response.message());
        return dResponse;
    }

}
