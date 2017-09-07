package com.decidir.sdk.services;

import java.io.IOException;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.confirms.ConfirmPaymentAmount;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.payments.PaymentResponse;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.resources.PaymentApi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import retrofit2.Response;

/**
 * Created by biandra on 25/11/16.
 */
public class PaymentConfirmService {

    public static final int HTTP_500 = 500;
    public static final int HTTP_402 = 402;
    private ErrorConverter errorConverter;
    private PaymentApi paymentApi;
    private PaymentConverter paymentConverter;

    private PaymentConfirmService(PaymentApi paymentApi, PaymentConverter paymentConverter, ErrorConverter errorConverter){
        this.paymentApi = paymentApi;
        this.paymentConverter = paymentConverter;
        this.errorConverter = errorConverter;
    }

    public static PaymentConfirmService getInstance(PaymentApi paymentApi) {
        return new PaymentConfirmService(paymentApi, new PaymentConverter(), new ErrorConverter());
    }

    public DecidirResponse<PaymentResponse> paymentConfirm(Long paymentId, Long amount, String user) {
        try {
            Response<PaymentResponse> response = this.paymentApi.paymentConfirm(user, paymentId, new ConfirmPaymentAmount(amount)).execute();
            return processPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    private DecidirResponse<PaymentResponse> processPaymentResponse(Response<PaymentResponse> response)
            throws IOException, JsonParseException, JsonMappingException {
        return this.paymentConverter.convertOrThrowError(response);
    }

}
