package com.decidir.sdk;


import com.decidir.sdk.configuration.DecidirConfiguration;
import com.decidir.sdk.dto.DecidirResponse;
import com.decidir.sdk.dto.Page;
import com.decidir.sdk.dto.Payment;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.resources.PaymentApi;
import com.decidir.sdk.services.PaymentsService;

public final class Decidir {

  private static String apiUrl = "https://api.decidir.com";
  private static Integer timeOut = 20;
  private PaymentsService paymentsService;

  public Decidir(final String secretAccessToken, final String apiUrl, final Integer timeOut) {
    if (apiUrl != null) {
      this.apiUrl = apiUrl;
    }
    if (timeOut != null){
      this.timeOut =timeOut;
    }
    this.paymentsService = PaymentsService.getInstance(DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, PaymentApi.class));
  }

  public Decidir(final String secretAccessToken) {
    this(secretAccessToken, null, null);
  }

  public Decidir(final String secretAccessToken, final Integer timeOut) {
    this(secretAccessToken, null, timeOut);
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
