package com.decidir.sdk.services;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.RefundConverter;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.AnnulRefundException;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.PaymentException;
import com.decidir.sdk.exceptions.RefundException;
import com.decidir.sdk.resources.RefundApi;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by biandra on 22/09/16.
 */
public class RefundsService {

    public static final int HTTP_500 = 500;
    public static final int HTTP_402 = 402;
    private RefundApi refundApi;
    private RefundConverter refundConverter;
    private ErrorConverter errorConverter;

    private RefundsService(RefundApi refundApi, RefundConverter refundConverter, ErrorConverter errorConverter){
        this.refundApi = refundApi;
        this.refundConverter = refundConverter;
        this.errorConverter = errorConverter;
    }

    public static RefundsService getInstance(RefundApi refundApi) {
        return new RefundsService(refundApi, new RefundConverter(), new ErrorConverter());
    }

    public DecidirResponse<RefundPaymentHistoryResponse> getRefunds(Long paymentId) {
        try {
            Response<RefundPaymentHistoryResponse> response = this.refundApi.getRefunds(paymentId).execute();
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

    public DecidirResponse<RefundPaymentResponse> refundPayment(Long paymentId, RefundPayment refundPayment, String user) {
        try {
            Response<RefundPaymentResponse> response = this.refundApi.refundPayment(user, paymentId, refundPayment).execute();
            return processRefundPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    private DecidirResponse<RefundPaymentResponse> processRefundPaymentResponse(Response<RefundPaymentResponse> response)
            throws IOException, JsonParseException, JsonMappingException {
        if (response.isSuccessful()) {
            return refundConverter.convert(response, response.body());
        } else {
            if (response.code() == HTTP_402){
                ObjectMapper objectMapper = new ObjectMapper();
                throw new RefundException(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), RefundPaymentResponse.class));
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        }
    }

    public DecidirResponse<AnnulRefundResponse> cancelRefund(Long paymentId, Long refundId, String user) {
        try {
            Response<AnnulRefundResponse> response = this.refundApi.cancelRefund(user, paymentId, refundId).execute();
            return processAnnulRefundResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    private DecidirResponse<AnnulRefundResponse> processAnnulRefundResponse(Response<AnnulRefundResponse> response)
            throws IOException, JsonParseException, JsonMappingException {
        if (response.isSuccessful()) {
            return refundConverter.convert(response, response.body());
        } else {
            if (response.code() == HTTP_402){
                ObjectMapper objectMapper = new ObjectMapper();
                throw new AnnulRefundException(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), AnnulRefundResponse.class));
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        }
    }
}
