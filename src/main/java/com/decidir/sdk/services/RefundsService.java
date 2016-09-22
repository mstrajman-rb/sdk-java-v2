package com.decidir.sdk.services;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.RefundConverter;
import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.RefundPayment;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.resources.RefundApi;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by biandra on 22/09/16.
 */
public class RefundsService {

    public static final int HTTP_500 = 500;
    public static final int HTTP_402 = 402;
    private static RefundsService service = null;
    private RefundApi refundApi;
    private RefundConverter refundConverter;
    private ErrorConverter errorConverter;

    private RefundsService(RefundApi refundApi, RefundConverter refundConverter, ErrorConverter errorConverter){
        this.refundApi = refundApi;
        this.refundConverter = refundConverter;
        this.errorConverter = errorConverter;
    }

    public static RefundsService getInstance(RefundApi refundApi) {
        if(service == null) {
            service = new RefundsService(refundApi, new RefundConverter(), new ErrorConverter());
        }
        return service;
    }

    public DecidirResponse<RefundPayment> refundPayment(Long paymentId) {
        try {
            Response<RefundPayment> response = this.refundApi.refundPayment(paymentId).execute();
            if (response.isSuccessful()) {
                return refundConverter.convert(response, response.body());
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<RefundPayment> cancelRefund(Long paymentId, Long refundId) {
        try {
            Response<RefundPayment> response = this.refundApi.cancelRefund(paymentId, refundId).execute();
            if (response.isSuccessful()) {
                return refundConverter.convert(response, response.body());
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
}
