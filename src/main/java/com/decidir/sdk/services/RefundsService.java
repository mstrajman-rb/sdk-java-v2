package com.decidir.sdk.services;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.dto.annullment.AnnulRefundResponse;
import com.decidir.sdk.dto.refunds.RefundMPOSPayment;
import com.decidir.sdk.dto.refunds.RefundPayment;
import com.decidir.sdk.dto.refunds.RefundPaymentHistoryResponse;
import com.decidir.sdk.dto.refunds.RefundPaymentResponse;
import com.decidir.sdk.exceptions.DecidirError;
import com.decidir.sdk.exceptions.responses.AnnulRefundException;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.responses.RefundException;
import com.decidir.sdk.resources.RefundApi;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import retrofit2.Response;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by biandra on 22/09/16.
 */
public class RefundsService {

    public static final int HTTP_500 = 500;
    private RefundApi refundApi;
    private PaymentConverter paymentConverter;
    private ErrorConverter errorConverter;

    private RefundsService(RefundApi refundApi, PaymentConverter paymentConverter, ErrorConverter errorConverter){
        this.refundApi = refundApi;
        this.paymentConverter = paymentConverter;
        this.errorConverter = errorConverter;
    }

    public static RefundsService getInstance(RefundApi refundApi) {
        return new RefundsService(refundApi, new PaymentConverter(), new ErrorConverter());
    }

    public DecidirResponse<RefundPaymentHistoryResponse> getRefunds(Long paymentId) {
        try {
            Response<RefundPaymentHistoryResponse> response = this.refundApi.getRefunds(paymentId).execute();
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

    public DecidirResponse<RefundPaymentResponse> refundPayment(Long paymentId, RefundPayment refundPayment, String user) {
        try {
            Response<RefundPaymentResponse> response = this.refundApi.refundPayment(user, paymentId, refundPayment).execute();
            return processRefundPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    public DecidirResponse<RefundPaymentResponse> refundMPOSPayment(Long paymentId, RefundMPOSPayment refundPayment, String user) {
        try {
            Response<RefundPaymentResponse> response = this.refundApi.refundMPOSPayment(user, paymentId, refundPayment).execute();
            return processRefundPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

    private DecidirResponse<RefundPaymentResponse> processRefundPaymentResponse(Response<RefundPaymentResponse> response)
            throws IOException, JsonParseException, JsonMappingException {
        DecidirResponse<RefundPaymentResponse> ret = null;

        try {
            ret = this.paymentConverter.convertOrThrowSpecError(response, RefundException.class, RefundPaymentResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public DecidirResponse<AnnulRefundResponse> cancelRefund(Long paymentId, Long refundId, String user) {
        DecidirResponse<AnnulRefundResponse> result = null;
        try {

            Response<AnnulRefundResponse> response = this.refundApi.cancelRefund(user, paymentId, refundId).execute();
            result = processAnnulRefundResponse(response);

        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private DecidirResponse<AnnulRefundResponse> processAnnulRefundResponse(Response<AnnulRefundResponse> response)
            throws IOException, JsonParseException, JsonMappingException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return this.paymentConverter.convertOrThrowSpecError(response, AnnulRefundException.class, AnnulRefundResponse.class);
    }
}
