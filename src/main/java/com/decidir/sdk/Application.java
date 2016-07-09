package com.decidir.sdk;


import com.decidir.sdk.configuration.DecidirConfiguration;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.services.PaymentsService;

public final class Application {

  static private String apiUrl = "https://api.decidir.com";
  private PaymentsService paymentsService;

  public Application(final String secretAccessToken, final String apiUrl) {
    if (apiUrl != null) {
      Application.apiUrl = apiUrl;
    }

    this.paymentsService = PaymentsService.getInstance(
            DecidirConfiguration.getInstance().initRetrofit(secretAccessToken, apiUrl));
  }

  public Application(final String secretAccessToken) {
    this(secretAccessToken, null);
  }

  public DecidirResponse<Payment> confirmPayment(Payment payment) throws DecidirException {
    return paymentsService.confirmPayment(payment);
  }

  public DecidirResponse<Page> getPayments(int offset, int pageSize) throws DecidirException {
    return paymentsService.getPayments(offset, pageSize);
  }

  public DecidirResponse<Payment> getPayment(int paymentId) throws DecidirException {
    return paymentsService.getPayment(paymentId);
  }

  public DecidirResponse<Payment> refundPayment(int paymentId) throws DecidirException {
    return paymentsService.refundPayment(paymentId);
  }

 }
