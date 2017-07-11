package com.decidir.sdk.services;

import java.io.IOException;

import com.decidir.sdk.converters.ErrorConverter;
import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.PaymentException;
import com.decidir.sdk.resources.PaymentApi;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Response;

/**
 * Created by biandra on 06/07/16.
 */
public class PaymentsService {

    public static final int HTTP_500 = 500;
    public static final int HTTP_402 = 402;
    private PaymentApi paymentApi;
    private PaymentConverter paymentConverter;
    private ErrorConverter errorConverter;

    private PaymentsService(PaymentApi paymentApi, PaymentConverter paymentConverter, ErrorConverter errorConverter){
        this.paymentApi = paymentApi;
        this.paymentConverter = paymentConverter;
        this.errorConverter = errorConverter;
    }

    public static PaymentsService getInstance(PaymentApi paymentApi) {
        return new PaymentsService(paymentApi, new PaymentConverter(), new ErrorConverter());
    }

    public DecidirResponse<PaymentResponse> paymentNoPci(PaymentRequest payment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.payNoPci(payment).execute();
            return processPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
    public DecidirResponse<PaymentResponse> paymentPciCard(PaymentPciCardRequest payment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.payPciCard(payment).execute();
            return processPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }
    public DecidirResponse<PaymentResponse> paymentPciToken(PaymentPciTokenRequest payment) {
        try {
            Response<PaymentResponse> response = this.paymentApi.payPciToken(payment).execute();
            return processPaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

	private DecidirResponse<PaymentResponse> processPaymentResponse(Response<PaymentResponse> response)
			throws IOException, JsonParseException, JsonMappingException {
		if (response.isSuccessful()) {
		    return paymentConverter.convert(response, response.body());
		} else {
		    if (response.code() == HTTP_402){
		        ObjectMapper objectMapper = new ObjectMapper();
		        throw new PaymentException(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), PaymentResponse.class));
		    } else {
		        DecidirResponse<DecidirError> error = errorConverter.convert(response);
		        throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
		    }
		}
	}

    private DecidirResponse<OfflinePaymentResponse> processOfflinePaymentResponse(Response<OfflinePaymentResponse> response)
            throws IOException, JsonParseException, JsonMappingException {
        if (response.isSuccessful()) {
            return paymentConverter.convert(response, response.body());
        } else {
            if (response.code() == HTTP_402){
                ObjectMapper objectMapper = new ObjectMapper();
                throw new PaymentException(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), PaymentResponse.class));
            } else {
                DecidirResponse<DecidirError> error = errorConverter.convert(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        }
    }

    public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize, String siteOperationId, String siteId) {
        try {
            Response<Page> response = this.paymentApi.getPayments(offset, pageSize, siteOperationId, siteId).execute();
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

    public DecidirResponse<PaymentResponse> getPayment(Long paymentId) {
        try {
            Response<PaymentResponse> response = this.paymentApi.getPayment(paymentId).execute();
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

    public DecidirResponse<OfflinePaymentResponse> offlinePayment(OfflinePaymentRequest offlinePayment) {
        try {
            Response<OfflinePaymentResponse> response = this.paymentApi.payOffline(offlinePayment).execute();
            return processOfflinePaymentResponse(response);
        } catch(IOException ioe) {
            throw new DecidirException(HTTP_500, ioe.getMessage());
        }
    }

}
