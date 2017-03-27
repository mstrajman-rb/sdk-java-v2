package com.decidir.sdk;

import com.decidir.sdk.configuration.DecidirConfiguration;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.PaymentException;
import com.decidir.sdk.resources.CardTokenApi;
import com.decidir.sdk.resources.PaymentApi;
import com.decidir.sdk.resources.RefundApi;
import com.decidir.sdk.services.CardTokenService;
import com.decidir.sdk.services.PaymentConfirmService;
import com.decidir.sdk.services.PaymentsService;
import com.decidir.sdk.services.RefundsService;


public final class Decidir {

	private static String apiUrl = "https://api.decidir.com";
	private static Integer timeOut = 20;
	private PaymentsService paymentsService;
	private RefundsService refundsService;
	private CardTokenService cardTokenService;
	private PaymentConfirmService paymentConfirmService;

	public Decidir(final String secretAccessToken, final String apiUrl, final Integer timeOut) {
		if (apiUrl != null) {
			this.apiUrl = apiUrl;
		}
		if (timeOut != null) {
			this.timeOut = timeOut;
		}
		this.paymentsService = PaymentsService.getInstance(
				DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, PaymentApi.class));
		this.refundsService = RefundsService.getInstance(
				DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, RefundApi.class));
		this.cardTokenService = CardTokenService.getInstance(
				DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, CardTokenApi.class));
		this.paymentConfirmService = PaymentConfirmService.getInstance(DecidirConfiguration
				.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, PaymentApi.class));
	}

	public Decidir(final String secretAccessToken) {
		this(secretAccessToken, null, null);
	}

	public Decidir(final String secretAccessToken, final Integer timeOut) {
		this(secretAccessToken, null, timeOut);
	}

	/**
	 * Generates a new payment with payment token
	 * 
	 * @param payment
	 *            {@link PaymentNoPciRequest} request
	 * @return a {@link DecidirResponse} with the approved {@link payment}
	 * @throws PaymentException
	 *             when the payment was rejected
	 * @throws DecidirException
	 *             when an error ocurrs
	 */
	public DecidirResponse<PaymentResponse> payment(PaymentNoPciRequest payment) throws PaymentException, DecidirException {
		return paymentsService.paymentNoPci(payment);
	}
	/**
	 * Generates a new pci payment with card data
	 * 
	 * @param payment
	 *            {@link PaymentPciCardRequest} request
	 * @return a {@link DecidirResponse} with the approved {@link payment}
	 * @throws PaymentException
	 *             when the payment was rejected
	 * @throws DecidirException
	 *             when an error ocurrs
	 */
	public DecidirResponse<PaymentResponse> payment(PaymentPciCardRequest payment) throws PaymentException, DecidirException {
		return paymentsService.paymentPciCard(payment);
	}
	/**
	 * Generates a new pci payment with card token
	 * 
	 * @param payment
	 *            {@link PaymentPciTokenRequest} request
	 * @return a {@link DecidirResponse} with the approved {@link Payment}
	 * @throws PaymentException
	 *             when the payment was rejected
	 * @throws DecidirException
	 *             when an error ocurrs
	 */
	public DecidirResponse<PaymentResponse> payment(PaymentPciTokenRequest payment) throws PaymentException, DecidirException {
		return paymentsService.paymentPciToken(payment);
	}

	/**
	 * Returns all site paymentes into a paginated list 
	 * @param offset (optional) index of the first payment to be shown in the list
	 * @param pageSize	(optional) size of the list to retrieve
	 * @param siteOperationId (optional) to list only payments with this site transaction id 
	 * @param siteId (optional) to list only payments of this siteId
	 * @return a {@link DecidirResponse} with the {@link Page} of payments
	 * @throws DecidirException when an error ocurrs
	 */
	public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize, String siteOperationId,
			String siteId) throws DecidirException {
		return paymentsService.getPayments(offset, pageSize, siteOperationId, siteId);
	}

	/**
	 * Retrieve payment by id
	 * @param paymentId
	 * @return a {@link DecidirResponse} with the {@link Payment} if exists
	 * @throws DecidirException if payment does not exist or an error ocurrs
	 */
	public DecidirResponse<PaymentResponse> getPayment(Long paymentId) throws DecidirException {
		return paymentsService.getPayment(paymentId);
	}

	/**
	 *
	 * @param paymentId
	 * @return
	 * @throws DecidirException when an error ocurrs
	 */
	public DecidirResponse<RefundPaymentHistoryResponse> getRefunds(Long paymentId) throws DecidirException {
		return refundsService.getRefunds(paymentId);
	}

	/**
	 *
	 * @param paymentId
	 * @param refundPayment
	 * @return
	 * @throws DecidirException when an error ocurrs
	 */
	public DecidirResponse<RefundPaymentResponse> refundPayment(Long paymentId, RefundPayment refundPayment)
			throws DecidirException {
		return refundsService.refundPayment(paymentId, refundPayment);
	}

	/**
	 *
	 * @param paymentId
	 * @param refundId
	 * @return
	 * @throws DecidirException when an error ocurrs
	 */
	public DecidirResponse<AnnulRefundResponse> cancelRefund(Long paymentId, Long refundId) throws DecidirException {
		return refundsService.cancelRefund(paymentId, refundId);
	}

	/**
	 * Lists user's card tokens
	 * 
	 * @param userSiteId
	 *            the userID
	 * @param bin
	 *            (optional) credit card bin
	 * @param lastFourDigits
	 *            (optional) last 4 digits of credit card
	 * @param expirationMonth
	 *            (optional) credit card's expiration month <code>[01-12]</code>
	 * @param expirationYear
	 *            (optional) credit card's expiration year <code>[00-99]</code>
	 * @return a {@link DecidirResponse} with a List with card tokens
	 * @throws DecidirException
	 *             when an error ocurrs
	 */
	public DecidirResponse<CardTokens> getCardTokens(String userSiteId, String bin, String lastFourDigits,
			String expirationMonth, String expirationYear) throws DecidirException {
		return cardTokenService.getCardTokens(userSiteId, bin, lastFourDigits, expirationMonth, expirationYear);
	}

	/**
	 * Deletes a persisted card token
	 * 
	 * @param token
	 *            a valid card Token
	 * @return a {@link DecidirResponse} when success
	 * @throws DecidirException
	 *             when an error ocurrs or token does not exist
	 */
	public DecidirResponse<Void> deleteCardToken(String token) throws DecidirException {
		return cardTokenService.deleteCardToken(token);
	}
	
	/**
	 * Confirm a pre approved payment
	 * @param paymentId
	 * @param amount (nullable) new payment amount
	 * @return @return a {@link DecidirResponse} when success
	 * @throws DecidirException when an error ocurrs 
	 */
	public DecidirResponse<PaymentResponse> confirmPayment(Long paymentId, Long amount)
			throws DecidirException {
		return paymentConfirmService.paymentConfirm(paymentId, amount);
	}

}
