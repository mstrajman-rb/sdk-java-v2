package com.decidir.sdk.services;

import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.decidir.sdk.resources.PaymentApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;

import java.io.IOException;

/**
 * Created by biandra on 06/07/16.
 */
public class PaymentsService {

    private static PaymentsService service = null;
    private PaymentApi paymentApi;

    private PaymentsService(PaymentApi paymentApi){
        this.paymentApi = paymentApi;
    }

    public static PaymentsService getInstance(PaymentApi paymentApi) {
        if(service == null) {
            service = new PaymentsService(paymentApi);
        }
        return service;
    }

    public DecidirResponse<Payment> confirmPayment(Payment payment) {
        return build(this.paymentApi.confirmPayment(payment));
    }

    public DecidirResponse<Page> payments(int offset, int pageSize) {
        return build(this.paymentApi.payments(offset, pageSize));
    }

    public DecidirResponse<Payment> getPayment(int paymentId) {
        return build(this.paymentApi.getPayment(paymentId));
    }

    public DecidirResponse<Payment> refundPayment(int paymentId) {
        return build(this.paymentApi.refundPayment(paymentId));
    }

    public static DecidirResponse build(Call call) {
        try {
            retrofit2.Response response = call.execute();
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.isSuccessful()) {
                DecidirResponse<Payment> decidirResponse = new DecidirResponse();
                //decidirResponse.setResult(objectMapper.readValue(response.body().toString(), Payment.class));
                decidirResponse.setResult((Payment)response.body());
                decidirResponse.setStatus(response.code());
                decidirResponse.setMessage(response.message());
                return decidirResponse;
            } else {
                DecidirResponse<DecidirError> decidirResponse = new DecidirResponse();
                decidirResponse.setResult(objectMapper.readValue(response.errorBody().string(), DecidirError.class));
                decidirResponse.setStatus(response.code());
                decidirResponse.setMessage(response.message());
                return decidirResponse;
            }

        } catch(IOException ioe) {
            DecidirResponse<DecidirError> decidirResponse = new DecidirResponse();
            decidirResponse.setStatus(500);
            decidirResponse.setMessage(ioe.getMessage());
            return decidirResponse;
        }
    }

}
