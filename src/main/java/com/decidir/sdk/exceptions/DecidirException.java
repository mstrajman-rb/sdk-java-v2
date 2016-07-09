package com.decidir.sdk.exceptions;

import com.decidir.sdk.dto.ApiError;
import com.decidir.sdk.dto.DecidirError;
import com.decidir.sdk.dto.NotFoundError;
import com.decidir.sdk.dto.ValidateError;

/**
 * Created by biandra on 08/07/16.
 */
public class DecidirException extends RuntimeException {

    private int status;
    private String message;

    static public DecidirException wrap(int status, String message, DecidirError decidirError) {
        if (decidirError instanceof ApiError){
            return new ApiException(status, message, (ApiError)decidirError);
        } else if (decidirError instanceof NotFoundError){
            return new NotFoundException(status, message, (NotFoundError)decidirError);
        } else if (decidirError instanceof ValidateError){
            return new ValidateException(status, message, (ValidateError)decidirError);
        } else {
            return new DecidirException(status, decidirError.getError_type());
        }
    }

    public DecidirException(int status, String message){
        this.status = status;
        this.message = message;
    }

    public DecidirException(){
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
