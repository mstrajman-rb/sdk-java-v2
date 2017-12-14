package com.decidir.sdk.configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by biandra on 06/07/16.
 */
public class DecidirConfiguration {

    static private final String version = "2.0.0";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String MAX_AGE_0 = "max-age=0";
    public static final String USER_AGENT = "User-Agent";
    public static final String DECIDIR_JAVA_SDK_V = "Decidir Java SDK v ";
    public static final String APIKEY = "apikey";


    public static <T> T initRetrofit(final String secretAccessToken, final String apiUrl, final Integer timeOut, final Class<T> serviceClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .connectTimeout(timeOut, TimeUnit.SECONDS);

        httpClient.networkInterceptors().add(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request request = chain.request().newBuilder()
                        .header(CACHE_CONTROL, MAX_AGE_0)
                        .header(APIKEY, secretAccessToken)
//                        .header("X-Consumer-Username", secretAccessToken+"_private")
//                        .header("X-Consumer-Username", secretAccessToken+"_pci")
                        .header(USER_AGENT, getUserAgent())
                        .build();

                return chain.proceed(request);
            }
        });
        
        ObjectMapper jacksonConverter = new ObjectMapper().addHandler(new DeserializationProblemHandler() {
            @Override
            public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException, JsonProcessingException {
                return true;
            }
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		jacksonConverter.setDateFormat(dateFormat);
		retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(JacksonConverterFactory.create(jacksonConverter))
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);
    }

    static private String getUserAgent() {
        return DECIDIR_JAVA_SDK_V + version;
    }

}
