package com.decidir.sdk;


import com.decidir.sdk.configuration.RetrofitConfiguration;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.decidir.sdk.dto.PaymentResult;
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

  public DecidirResponse<PaymentResult> confirmPayment(Payment payment) {
    return paymentsService.confirmPayment(payment);
  }

  public DecidirResponse<Page> getPayments(int offset, int pageSize) {
    return paymentsService.getPayments(offset, pageSize);
  }

  public DecidirResponse<PaymentResult> getPayment(int paymentId) {
    return paymentsService.getPayment(paymentId);
  }

  public DecidirResponse<PaymentResult> refundPayment(int paymentId) {
    return paymentsService.refundPayment(paymentId);
  }

 }
