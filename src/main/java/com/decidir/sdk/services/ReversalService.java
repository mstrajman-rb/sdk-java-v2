package com.decidir.sdk.services;

import com.decidir.sdk.converters.PaymentConverter;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.reverse.ReversePaymentResponse;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.responses.ReverseException;
import com.decidir.sdk.resources.ReversalApi;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by gustavo on 4/18/18.
 */
public class ReversalService {
    private static final int HTTP_500 = 500;
    private ReversalApi api;
    private PaymentConverter paymentConverter;

    private ReversalService(ReversalApi api, PaymentConverter paymentConverter) {
        this.api = api;
        this.paymentConverter = paymentConverter;
    }

    public static ReversalService getInstance(ReversalApi api) {
        return new ReversalService(api, new PaymentConverter());
    }

    public DecidirResponse<ReversePaymentResponse> refundPayment(@Header("User") String user, @Path("paymentId") Long paymentId) {
        try {
            Response<ReversePaymentResponse> response = this.api.refundPayment(user, paymentId).execute();
            return this.paymentConverter.convertOrThrowSpecError(response, ReverseException.class, ReversePaymentResponse.class);
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new DecidirException(HTTP_500, e.getMessage());
        }
    }
}
