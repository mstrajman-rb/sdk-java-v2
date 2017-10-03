package com.decidir.sdk.converters;

import java.io.IOException;

import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.exceptions.DecidirError;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Response;

/**
 * Created by biandra on 04/08/16.
 */
public class ErrorConverter {

    public DecidirResponse<DecidirError> convert(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DecidirResponse<DecidirError> dResponse = new DecidirResponse();
        dResponse.setStatus(response.code());
        dResponse.setResult(objectMapper.readValue(response.errorBody().string(), DecidirError.class));
        dResponse.setMessage(response.message());
        return dResponse;
    }
}
