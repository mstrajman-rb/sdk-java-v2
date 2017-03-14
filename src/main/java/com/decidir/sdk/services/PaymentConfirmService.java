package com.decidir.sdk.services;

import java.io.IOException;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.PaymentNoPciRequest;
import com.decidir.sdk.dto.PaymentResponse;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.resources.PaymentApi;

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
        this.errorConverter = errorConverter;
    }

    public static PaymentConfirmService getInstance(PaymentApi paymentApi) {
        return new PaymentConfirmService(paymentApi, new PaymentConverter(), new ErrorConverter());
    }

    public DecidirResponse<PaymentResponse> paymentConfirm(Long paymentId, PaymentNoPciRequest confirmPayment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.paymentConfirm(paymentId, confirmPayment).execute();
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
