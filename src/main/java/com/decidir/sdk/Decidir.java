package com.decidir.sdk;


import com.decidir.sdk.configuration.DecidirConfiguration;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.services.PaymentsService;

public final class Decidir {

  static private String apiUrl = "https://api.decidir.com";
  private PaymentsService paymentsService;

  public Decidir(final String secretAccessToken, final String apiUrl) {
    if (apiUrl != null) {
      Decidir.apiUrl = apiUrl;
    }

    this.paymentsService = PaymentsService.getInstance(DecidirConfiguration.initRetrofit(secretAccessToken, apiUrl));
  }

  public Decidir(final String secretAccessToken) {
    this(secretAccessToken, null);
  }

  public DecidirResponse<Payment> confirmPayment(Payment payment) throws DecidirException {
    return paymentsService.confirmPayment(payment);
  }

  public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize) throws DecidirException {
    return paymentsService.getPayments(offset, pageSize);
  }

  public DecidirResponse<Payment> getPayment(Long paymentId) throws DecidirException {
    return paymentsService.getPayment(paymentId);
  }

  public DecidirResponse<Payment> refundPayment(Long paymentId) throws DecidirException {
    return paymentsService.refundPayment(paymentId);
  }

 }
