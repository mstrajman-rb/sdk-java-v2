package com.decidir.sdk.converters;

import com.decidir.sdk.dto.AnnulRefundResponse;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.PaymentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.PaymentResponse;
/**
 * Created by biandra on 08/07/16.
 */
public class PaymentConverter {
    public static final int HTTP_402 = 402;

    public <A> DecidirResponse<A>  convert(Response<A> response, A body) {
        DecidirResponse<A> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(body);
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public <A> DecidirResponse<A> convertOrThrowError(Response<A> response) throws IOException {
        if (response.isSuccessful()) {
            return this.convert(response, response.body());
        } else {
            if (response.code() == HTTP_402){
                ObjectMapper objectMapper = new ObjectMapper();
                throw new PaymentException(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), PaymentResponse.class));
            } else {
                DecidirResponse<DecidirError> error = this.convertError(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        }
    }

    public DecidirResponse<DecidirError> convertError(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DecidirError decidirError = objectMapper.readValue(response.errorBody().string(), DecidirError.class);
        return this.convert(response,decidirError);
    }

    public <A,E extends RuntimeException> DecidirResponse<A> convertOrThrowSpecError(Response<A> response, Class specError) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (response.isSuccessful()) {
            return this.convert(response, response.body());
        } else {
            if (response.code() == HTTP_402){
                ObjectMapper objectMapper = new ObjectMapper();
                Constructor<E> ctor = specError.getConstructor();
                Class[] cArg = new Class[3];
                cArg[0] = int.class;
                cArg[1] = String.class;
                cArg[2] = AnnulRefundResponse.class;
                throw ctor.newInstance(response.code(), response.message(), objectMapper.readValue(response.errorBody().string(), specError));
            } else {
                DecidirResponse<DecidirError> error = this.convertError(response);
                throw DecidirException.wrap(error.getStatus(), error.getMessage(), error.getResult());
            }
        }
    }


}
