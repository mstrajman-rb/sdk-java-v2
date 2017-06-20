package com.decidir.sdk.resources;

import com.decidir.sdk.dto.DataForm;
import com.decidir.sdk.dto.FormResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by biandra on 19/06/17.
 */
public interface FormApi {

    @POST("validate")
    Call<FormResponse> validate(@Body DataForm dataForm);
}
