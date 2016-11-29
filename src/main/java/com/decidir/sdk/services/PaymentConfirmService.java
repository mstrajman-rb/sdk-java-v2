package com.decidir.sdk.services;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConfirmConverter;
import com.decidir.sdk.dto.ConfirmPayment;
import com.decidir.sdk.dto.ConfirmPaymentResponse;
import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.resources.PaymentConfirmApi;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by biandra on 25/11/16.
 */
public class PaymentConfirmService {

    public static final int HTTP_500 = 500;
    public static final int HTTP_402 = 402;
    private ErrorConverter errorConverter;
    private PaymentConfirmApi paymentConfirmApi;
    private PaymentConfirmConverter paymentConfirmConverter;

    private PaymentConfirmService(PaymentConfirmApi paymentConfirmApi, PaymentConfirmConverter paymentConfirmConverter, ErrorConverter errorConverter){
        this.paymentConfirmApi = paymentConfirmApi;
        this.paymentConfirmConverter = paymentConfirmConverter;
        this.errorConverter = errorConverter;
    }

    public static PaymentConfirmService getInstance(PaymentConfirmApi paymentConfirmApi) {
        return new PaymentConfirmService(paymentConfirmApi, new PaymentConfirmConverter(), new ErrorConverter());
    }

    public DecidirResponse<ConfirmPaymentResponse> paymentConfirm(Long paymentId, ConfirmPayment confirmPayment) {
        try {
            Response<ConfirmPaymentResponse> response = this.paymentConfirmApi.paymentConfirm(paymentId, confirmPayment).execute();
            if (response.isSuccessful()) {
                return paymentConfirmConverter.convert(response, response.body());
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<ConfirmPaymentResponse> getConfirm(Long paymentId) {
        try {
            Response<ConfirmPaymentResponse> response = this.paymentConfirmApi.getPaymentConfirm(paymentId).execute();
            if (response.isSuccessful()) {
                return paymentConfirmConverter.convert(response, response.body());
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
}
