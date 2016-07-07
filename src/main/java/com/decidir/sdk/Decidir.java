package com.decidir.sdk;


import com.decidir.sdk.configuration.RetrofitConfiguration;
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

    this.paymentsService = PaymentsService.getInstance(
            RetrofitConfiguration.getInstance().initRetrofit(secretAccessToken, apiUrl));
  }

  public Decidir(final String secretAccessToken) {
    this(secretAccessToken, null);
  }

  public DecidirResponse<Payment> confirmPayment(Payment payment) {
    return paymentsService.confirmPayment(payment);
  }

  public DecidirResponse<Page> payments(int offset, int pageSize) {
    return paymentsService.payments(offset, pageSize);
  }

  public DecidirResponse<Payment> getPayment(int paymentId) {
    return paymentsService.getPayment(paymentId);
  }

  public DecidirResponse<Payment> refundPayment(int paymentId) {
    return paymentsService.refundPayment(paymentId);
  }

 }
