package com.decidir.sdk.configuration;

import com.decidir.sdk.resources.PaymentApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * Created by biandra on 06/07/16.
 */
public class DecidirConfiguration {

    private static DecidirConfiguration retrofitConf = new DecidirConfiguration();
    static private final String version = "0.1.3";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String MAX_AGE_0 = "max-age=0";
    public static final String X_CONSUMER_USERNAME = "X-Consumer-Username";
    public static final String USER_AGENT = "User-Agent";
    public static final String PRIVATE = "_private";
    public static final String DECIDIR_JAVA_SDK_V = "Decidir Java SDK v ";

    private DecidirConfiguration(){}

    public static DecidirConfiguration getInstance() {
        return retrofitConf;
    }

    public PaymentApi initRetrofit(final String secretAccessToken, final String apiUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.networkInterceptors().add(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request request = chain.request().newBuilder()
                        .header(CACHE_CONTROL, MAX_AGE_0)
                        .header(X_CONSUMER_USERNAME, secretAccessToken + PRIVATE)
                        .header(USER_AGENT, getUserAgent())
                        .build();

                return chain.proceed(request);
            }
        });

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(com.decidir.sdk.resources.PaymentApi.class);
    }

    static private String getUserAgent() {
        return DECIDIR_JAVA_SDK_V + version;
    }

}
