package com.decidir.sdk.services;

import com.decidir.sdk.dto.*;
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

    public DecidirResponse<PaymentResult> confirmPayment(Payment payment) {
        return buildResponse(this.paymentApi.confirmPayment(payment));
    }

    public DecidirResponse<Page> getPayments(int offset, int pageSize) {
        return buildPaymentsResponse(this.paymentApi.getPayments(offset, pageSize));
    }

    public DecidirResponse<PaymentResult> getPayment(int paymentId) {
        return buildResponse(this.paymentApi.getPayment(paymentId));
    }

    public DecidirResponse<PaymentResult> refundPayment(int paymentId) {
        return buildResponse(this.paymentApi.refundPayment(paymentId));
    }

    //TODO:converter
    private DecidirResponse<PaymentResult> buildResponse(Call call) {
        try {
            retrofit2.Response response = call.execute();
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.isSuccessful()) {
                DecidirResponse<PaymentResult> decidirResponse = new DecidirResponse();
                PaymentResult paymentResult= new PaymentResult();
                //decidirResponse.setResult(objectMapper.readValue(response.body().toString(), Payment.class));
                paymentResult.setPayment((Payment) response.body());
                decidirResponse.setStatus(response.code());
                decidirResponse.setResult(paymentResult);
                decidirResponse.setMessage(response.message());
                return decidirResponse;
            } else {
                DecidirResponse<PaymentResult> decidirResponse = new DecidirResponse();
                PaymentResult paymentResult= new PaymentResult();
                paymentResult.setErrorDetail(objectMapper.readValue(response.errorBody().string(), DecidirError.class));
                decidirResponse.setStatus(response.code());
                decidirResponse.setResult(paymentResult);
                decidirResponse.setMessage(response.message());
                return decidirResponse;
            }

        } catch(IOException ioe) {
            DecidirResponse<PaymentResult> decidirResponse = new DecidirResponse();
            decidirResponse.setStatus(500);
            decidirResponse.setMessage(ioe.getMessage());
            return decidirResponse;
        }
    }

    //TODO:converter
    private DecidirResponse<Page> buildPaymentsResponse(Call call) {
        try {
            retrofit2.Response response = call.execute();
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.isSuccessful()) {
                DecidirResponse<Page> decidirResponse = new DecidirResponse();
                //decidirResponse.setResult(objectMapper.readValue(response.body().toString(), Page.class));
                decidirResponse.setStatus(response.code());
                decidirResponse.setResult((Page)response.body());
                decidirResponse.setMessage(response.message());
                return decidirResponse;
            } else {
                DecidirResponse<Page> decidirResponse = new DecidirResponse();
                decidirResponse.setStatus(response.code());
                decidirResponse.setMessage(response.message());
                return decidirResponse;
            }

        } catch(IOException ioe) {
            DecidirResponse<Page> decidirResponse = new DecidirResponse();
            decidirResponse.setStatus(500);
            decidirResponse.setMessage(ioe.getMessage());
            return decidirResponse;
        }
    }

}
