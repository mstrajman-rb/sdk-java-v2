package com.decidir.sdk;


import com.decidir.sdk.configuration.DecidirConfiguration;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.resources.CardTokenApi;
import com.decidir.sdk.resources.PaymentApi;
import com.decidir.sdk.resources.RefundApi;
import com.decidir.sdk.services.CardTokenService;
import com.decidir.sdk.services.PaymentsService;
import com.decidir.sdk.services.RefundsService;

public final class Decidir {

  private static String apiUrl = "https://api.decidir.com";
  private static Integer timeOut = 20;
  private PaymentsService paymentsService;
  private RefundsService refundsService;
  private CardTokenService cardTokenService;

  public Decidir(final String secretAccessToken, final String apiUrl, final Integer timeOut) {
    if (apiUrl != null) {
      this.apiUrl = apiUrl;
    }
    if (timeOut != null){
      this.timeOut =timeOut;
    }
    this.paymentsService = PaymentsService.getInstance(DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, PaymentApi.class));
    this.refundsService = RefundsService.getInstance(DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, RefundApi.class));
    this.cardTokenService = CardTokenService.getInstance(DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, CardTokenApi.class));
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

  public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize, String siteOperationId, String merchantId) throws DecidirException {
    return paymentsService.getPayments(offset, pageSize, siteOperationId, merchantId);
  }

  public DecidirResponse<Payment> getPayment(Long paymentId) throws DecidirException {
    return paymentsService.getPayment(paymentId);
  }

  public DecidirResponse<RefundPaymentHistoryResponse> getRefunds(Long paymentId) throws DecidirException {
    return refundsService.getRefunds(paymentId);
  }

  public DecidirResponse<RefundPaymentResponse> refundPayment(Long paymentId, RefundPayment refundPayment) throws DecidirException {
    return refundsService.refundPayment(paymentId, refundPayment);
  }

  public DecidirResponse<RefundPaymentResponse> cancelRefund(Long paymentId, Long refundId) throws DecidirException {
    return refundsService.cancelRefund(paymentId, refundId);
  }

  public DecidirResponse<CardTokens> getCardTokens(String userSiteId, String bin, String lastFourDigits, String expirationMonth, String expirationYear) throws DecidirException {
    return cardTokenService.getCardTokens(userSiteId, bin, lastFourDigits, expirationMonth, expirationYear);
  }

  public DecidirResponse<Void> deleteCardToken(String token) throws DecidirException {
    return cardTokenService.deleteCardToken(token);
  }

 }
