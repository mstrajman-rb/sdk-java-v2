package com.decidir.sdk;

import java.io.IOException;

import com.decidir.sdk.dto.DecidirResponse;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import com.decidir.sdk.api.PaymentApi;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;

/**
 * TODO: Falta manejo de errores
 * TODO: Manejo URL api
 */
public final class Decidir {

  static private String apiUrl = "https://api.decidir.com";

  static private final String version = "0.1.0";

  static private String secretAccessToken;

  private Boolean sandbox = Boolean.TRUE;

  private PaymentApi paymentApi;

  public Decidir(final String secretAccessToken, final String apiUrl) {

    Decidir.secretAccessToken = secretAccessToken;

    if (apiUrl != null)
      Decidir.apiUrl = apiUrl;

    initRetrofit();
  }

  public Decidir(final String secretAccessToken) {

    this(secretAccessToken, null);
  }

  public DecidirResponse<Payment> confirmPayment(Payment payment) {

    return DecidirResponse.build(this.paymentApi.confirmPayment(payment));
  }

  public DecidirResponse<Page> payments() throws IOException {

    return DecidirResponse.build(this.paymentApi.payments());
  }

  public DecidirResponse<Payment> getPayment(int paymentId) {

    return DecidirResponse.build(this.paymentApi.getPayment(paymentId));
  }

  public DecidirResponse<Payment> cancelPayment(int paymentId) throws IOException {

    return DecidirResponse.build(this.paymentApi.deletePayment(paymentId));
  }

  static private String getUserAgent() {

    return "Decidir Java SDK v"+Decidir.version;
  }

  //////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////
  private void initRetrofit() {

//    GsonBuilder gsonBuilder = new GsonBuilder();
//
//    gsonBuilder.registerTypeAdapter(Page.class, new PageDeserializer());
//
//    Gson gson = gsonBuilder.create();

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    httpClient.networkInterceptors().add(new Interceptor() {

      @Override
      public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

          Request request = chain.request().newBuilder()
            .header("Cache-Control", "max-age=0")
            //.header("Decidir-Access-Token", Decidir.secretAccessToken)
            .header("X-Consumer-Username", Decidir.secretAccessToken+"_private")
            .header("User-Agent", Decidir.getUserAgent())
            .build();

          return chain.proceed(request);
      }

    });

    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(Decidir.apiUrl)
      .addConverterFactory(JacksonConverterFactory.create())
      .client(httpClient.build())
      .build();

    this.paymentApi = retrofit.create(com.decidir.sdk.api.PaymentApi.class);
  }



//  private class PageDeserializer implements JsonDeserializer<Page<?>> {
//
//    //TODO: Hacer esto para que ande generico
//    public Page<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//      throws JsonParseException {
//
//        Page<Payment> page = new Page<Payment>();
//
//        page.total = json.getAsJsonObject().get("total").getAsInt();
//        page.offset = json.getAsJsonObject().get("offset").getAsInt();
//        page.limit = json.getAsJsonObject().get("limit").getAsInt();
//        Type listPaymentType = new TypeToken<List<Payment>>(){}.getType();
//        page.results = context.deserialize(json.getAsJsonObject().get("results").getAsJsonArray(), listPaymentType);
//
//        return page;
//    }
//  }

 }
