package com.decidir.sdk.services;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.PaymentException;
import com.decidir.sdk.resources.PaymentApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by biandra on 06/07/16.
 */
public class PaymentsService {

    public static final int HTTP_500 = 500;
    public static final int HTTP_402 = 402;
    private static PaymentsService service = null;
    private PaymentApi paymentApi;
    private PaymentConverter paymentConverter;
    private ErrorConverter errorConverter;

    private PaymentsService(PaymentApi paymentApi, PaymentConverter paymentConverter, ErrorConverter errorConverter){
        this.paymentApi = paymentApi;
        this.paymentConverter = paymentConverter;
        this.errorConverter = errorConverter;
    }

    public static PaymentsService getInstance(PaymentApi paymentApi) {
        if(service == null) {
            service = new PaymentsService(paymentApi, new PaymentConverter(), new ErrorConverter());
        }
        return service;
    }

    public DecidirResponse<Payment> confirmPayment(Payment payment) {
        try {
            Response<Payment> response = this.paymentApi.confirmPayment(payment).execute();
            if (response.isSuccessful()) {
                return paymentConverter.convert(response, response.body());
            } else {
                if (response.code() == HTTP_402){
                    ObjectMapper objectMapper = new ObjectMapper();
                    throw new PaymentException(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), Payment.class));
                } else {
                    DecidirResponse<DecidirError> error = errorConverter.convert(response);
                    throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
                }
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize) {
        try {
            Response<Page> response = this.paymentApi.getPayments(offset, pageSize).execute();
            if (response.isSuccessful()) {
                return paymentConverter.convert(response, response.body());
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<Payment> getPayment(Long paymentId) {
        try {
            Response<Payment> response = this.paymentApi.getPayment(paymentId).execute();
            if (response.isSuccessful()) {
                return paymentConverter.convert(response, response.body());
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

}
