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

    public DecidirResponse<PaymentResponse> convert(Response<PaymentResponse> response, PaymentResponse payment){
        DecidirResponse<PaymentResponse> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(payment);
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public DecidirResponse<Page> convert(Response<Page> response, Page page) {
        DecidirResponse<Page> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(page);
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public DecidirResponse<OfflinePaymentResponse> convert(Response<OfflinePaymentResponse> response, OfflinePaymentResponse payment){
        DecidirResponse<OfflinePaymentResponse> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(payment);
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public DecidirResponse<GDSPaymentResponse> convert(Response<GDSPaymentResponse> response, GDSPaymentResponse body) {
        DecidirResponse<GDSPaymentResponse> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(body);
        dResponse.setMessage(response.message());
        return dResponse;
    }
}
