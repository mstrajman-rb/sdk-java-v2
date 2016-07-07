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

    private PaymentApi paymentApi;

    public PaymentsService(PaymentApi paymentApi){
        this.paymentApi = paymentApi;
    }

    public DecidirResponse<Payment> confirmPayment(Payment payment) {
        return build(this.paymentApi.confirmPayment(payment));
    }

    public DecidirResponse<Page> payments() {
        return build(this.paymentApi.payments());
    }

    public DecidirResponse<Payment> getPayment(int paymentId) {
        return build(this.paymentApi.getPayment(paymentId));
    }

    public DecidirResponse<Payment> cancelPayment(int paymentId) {
        return build(this.paymentApi.deletePayment(paymentId));
    }

    public static DecidirResponse build(Call call) {
        try {
            retrofit2.Response response = call.execute();
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.isSuccessful()) {
                DecidirResponse<Payment> decidirResponse = new DecidirResponse();
                //TODO: por q no lo toma??
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
