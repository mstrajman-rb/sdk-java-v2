package com.decidir.sdk.converters;

import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by biandra on 08/07/16.
 */
public class PaymentConverter {

    public DecidirResponse<Payment> convert(Response<Payment> response, Payment payment){
        DecidirResponse<Payment> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(payment);
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public DecidirResponse<DecidirError> convert(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DecidirResponse<DecidirError> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(objectMapper.readValue(response.errorBody().string(), DecidirError.class));
        dResponse.setMessage(response.message());
        return dResponse;
    }

    public DecidirResponse<Page> convert(Response<Page> response, Page page) {
        DecidirResponse<Page> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(page);
        dResponse.setMessage(response.message());
        return dResponse;
    }
}
