package com.decidir.sdk;


import com.decidir.sdk.configuration.Retrofit;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.decidir.sdk.services.PaymentsService;

/**
 * TODO: Manejo URL api
 */
public final class Decidir {

  static private String apiUrl = "https://api.decidir.com";
  private PaymentsService paymentsService;

  public Decidir(final String secretAccessToken, final String apiUrl) {
    if (apiUrl != null) {
      Decidir.apiUrl = apiUrl;
    }

    this.paymentsService = new PaymentsService(Retrofit.initRetrofit(secretAccessToken, apiUrl));
  }

  public Decidir(final String secretAccessToken) {
    this(secretAccessToken, null);
  }

  public DecidirResponse<Payment> confirmPayment(Payment payment) {
    return paymentsService.confirmPayment(payment);
  }

  public DecidirResponse<Page> payments() {
    return paymentsService.payments();
  }

  public DecidirResponse<Payment> getPayment(int paymentId) {
    return paymentsService.getPayment(paymentId);
  }

  public DecidirResponse<Payment> cancelPayment(int paymentId) {
    return paymentsService.cancelPayment(paymentId);
  }

 }
