package com.decidir.sdk;

import com.decidir.sdk.configuration.DecidirConfiguration;
import com.decidir.sdk.dto.*;
import com.decidir.sdk.exceptions.AnnulRefundException;
import com.decidir.sdk.exceptions.DecidirException;
import com.decidir.sdk.exceptions.PaymentException;
import com.decidir.sdk.exceptions.RefundException;
import com.decidir.sdk.resources.CardTokenApi;
import com.decidir.sdk.resources.PaymentApi;
import com.decidir.sdk.resources.RefundApi;
import com.decidir.sdk.services.CardTokenService;
import com.decidir.sdk.services.PaymentConfirmService;
import com.decidir.sdk.services.PaymentsService;
import com.decidir.sdk.services.RefundsService;


public final class Decidir {

	private static String apiUrl = "https://live.decidir.com/api/v1";
	private static Integer timeOut = 20;
	private PaymentsService paymentsService;
	private RefundsService refundsService;
	private CardTokenService cardTokenService;
	private PaymentConfirmService paymentConfirmService;

	/**
	 * Creates a new instance to communicate with Decidir Api.  
	 * @param secretAccessToken the provided apikey
	 * @param apiUrl Decidir Api URL. If URL is not provided, <em><strong>https://live.decidir.com/api/v1</strong></em> will be used
	 * @param timeOut sets the request timeout expressed in seconds, by default sets <strong>20 seconds</strong> if no value is provided. <strong>Must</strong> be greater than 0 (zero)
	 * @throws IllegalArgumentException if an invalid timeout value is used
	 * <br>
	 * <br>
	 * <ul>
	 * <li>
	 * <strong>Example 1 - </strong>Instantiate Decidir with provided apikey, another URL and 5 seconds timeout
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0", "https://developers.decidir.com/api/v1", 5);
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * 
	 * <li>
	 * <strong>Example 2 - </strong>Instantiate Decidir with provided apikey and another URL, with default timeout
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0", "https://developers.decidir.com/api/v1", null);
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * 
	 * <li>
	 * <strong>Example 3 - </strong>Instantiate Decidir with provided apikey and 3 seconds timeout, with default URL
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0",null, 3);
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * 
	 * <li>
	 * <strong>Example 4 - </strong>Instantiate Decidir only with provided apikey
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0",null, null);
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * </ul>
	 */
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
		this.paymentConfirmService = PaymentConfirmService.getInstance(
				DecidirConfiguration.initRetrofit(secretAccessToken, this.apiUrl, this.timeOut, PaymentApi.class));
	}
	
	/**
	 * Creates a new instance to communicate with Decidir Api. Sends requests to <em><strong>production(https://live.decidir.com/api/v1)</strong></em> with 20 seconds timeout   
	 * @param secretAccessToken the provided apikey
	 * <br>
	 * <br>
	 * <ul>
	 * <li>
	 * <strong>Example</strong>
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0");
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * </ul>
	 */
	public Decidir(final String secretAccessToken) {
		this(secretAccessToken, null, null);
	}

	/**
	 * Creates a new instance to communicate with Decidir Api. Sends requests to <em><strong>production(https://live.decidir.com/api/v1)</strong></em>  
	 * @param secretAccessToken the provided apikey
	 * @param timeOut sets the request timeout expressed in seconds, by default sets <strong>20 seconds</strong> if no value is provided. <strong>Must</strong> be greater than 0 (zero)
	 * @throws IllegalArgumentException if an invalid timeout value is used
	 * <br>
	 * <br>
	 * <ul>
	 * <li>
	 * <strong>Example 1 - </strong>Instantiate Decidir with provided apikey and 3 seconds timeout
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0", 3);
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * 
	 * <li>
	 * <strong>Example 2 - </strong>Instantiate Decidir only with provided apikey and default 20 seconds timeout
	 * <pre>{@code import com.decidir.sdk.Decidir;
	 * 
	 * public class MyClass {
	 *	Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0",null);
	 *	...
	 * }
	 * </pre>
	 * </li>
	 * </ul>
	 */
	public Decidir(final String secretAccessToken, final Integer timeOut) {
		this(secretAccessToken, null, timeOut);
	}

	/**
	 * Executes a new payment using a generated payment token
	 * 
	 * @param payment
	 *            {@link PaymentRequest} request
	 * @return a {@link DecidirResponse} with the approved {@link Payment}
	 * @throws PaymentException
	 *             when the payment was rejected
	 * @throws DecidirException
	 *             when an error occurs
	 * <br>
	 * <br>
	 * <strong>Usage example</strong>
	 * <pre>
	 * {@code ...
	 * Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0");
	 * PaymentRequest paymentRequest = new PaymentRequest();
	 * //Fill payment request data - i.e. see {@link PaymentRequest}
	 * ...
	 * try {
	 *	DecidirResponse<PaymentResponse> paymentResponse = decidir.payment(paymentRequest);
	 *	//process payment response - see {@link DecidirResponse}
	 *	...
	 *	} catch (PaymentException pe) {
	 *	 //Handle rejected payment - see {@link PaymentException}
	 *	 ...
	 *	} catch (DecidirException de) {
	 *	 //Handle returned api exception - see {@link DecidirException}
	 *	 ...
	 *	} catch (Exception e) {
	 *	 //Handle exception
	 *	 ...
	 *	}
	 *	...
	 * }
	 * </pre>
	 * @see #payment(PaymentPciCardRequest)
	 * @see #payment(PaymentPciTokenRequest)
	 * @see #confirmPayment(Long, Long, String)
	 * @see #getPayment(Long)
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #refundPayment(Long, RefundPayment, String)
	 */
	public DecidirResponse<PaymentResponse> payment(PaymentRequest payment) throws PaymentException, DecidirException {
		return paymentsService.paymentNoPci(payment);
	}
	/**
	 * Executes a new payment(PCI) using the card data
	 * 
	 * @param payment
	 *            {@link PaymentPciCardRequest} request
	 * @return a {@link DecidirResponse} with the approved {@link Payment}
	 * @throws PaymentException
	 *             when the payment was rejected
	 * @throws DecidirException
	 *             when an error occurs
	 * <br>
	 * <br>
	 * <strong>Usage example</strong>
	 * <pre>
	 * {@code ...
	 * Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0");
	 * PaymentPciCardRequest paymentRequest = new PaymentPciCardRequest();
	 * //Fill payment request data - i.e. see {@link PaymentPciCardRequest}
	 * ...
	 * try {
	 *	DecidirResponse<PaymentResponse> paymentResponse = decidir.payment(paymentRequest);
	 *	//process payment response - see {@link DecidirResponse}
	 *	...
	 *	} catch (PaymentException pe) {
	 *	 //Handle rejected payment - see {@link PaymentException}
	 *	 ...
	 *	} catch (DecidirException de) {
	 *	 //Handle returned api exception - see {@link DecidirException}
	 *	 ...
	 *	} catch (Exception e) {
	 *	 //Handle exception
	 *	 ...
	 *	}
	 *	...
	 * }
	 * </pre>            
	 * @see #payment(PaymentRequest)
	 * @see #payment(PaymentPciTokenRequest)
	 * @see #confirmPayment(Long, Long, String)
	 * @see #getPayment(Long)
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #refundPayment(Long, RefundPayment, String)
	 */
	public DecidirResponse<PaymentResponse> payment(PaymentPciCardRequest payment) throws PaymentException, DecidirException {
		return paymentsService.paymentPciCard(payment);
	}
	/**
	 * Executes a new payment(PCI) using the card token data
	 * 
	 * @param payment
	 *            {@link PaymentPciTokenRequest} request
	 * @return a {@link DecidirResponse} with the approved {@link Payment}
	 * @throws PaymentException
	 *             when the payment was rejected
	 * @throws DecidirException
	 *             when an error occurs
	 * <br>
	 * <br>
	 * <strong>Usage example</strong>
	 * <pre>
	 * {@code ...
	 * Decidir decidir = new Decidir("f9c44926d1584f2d9b90e7c1d102cbe0");
	 * PaymentPciTokenRequest paymentRequest = new PaymentPciTokenRequest();
	 * //Fill payment request data - i.e. see {@link PaymentPciTokenRequest}
	 * ...
	 * try {
	 *	DecidirResponse<PaymentResponse> paymentResponse = decidir.payment(paymentRequest);
	 *	//process payment response - see {@link DecidirResponse}
	 *	...
	 *	} catch (PaymentException pe) {
	 *	 //Handle rejected payment - see {@link PaymentException}
	 *	 ...
	 *	} catch (DecidirException de) {
	 *	 //Handle returned api exception - see {@link DecidirException}
	 *	 ...
	 *	} catch (Exception e) {
	 *	 //Handle exception
	 *	 ...
	 *	}
	 *	...
	 * }
	 * </pre>    
	 * @see #payment(PaymentRequest)
	 * @see #payment(PaymentPciCardRequest)
	 * @see #confirmPayment(Long, Long, String)
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #getPayment(Long) 
	 * @see #refundPayment(Long, RefundPayment, String)
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
	 * @throws DecidirException when an error occurs
	 * 
	 * @see #getPayment(Long)
	 * @see #getRefunds(Long)
	 * @see #getCardTokens(String, String, String, String, String)
	 * @see #confirmPayment(Long, Long, String)
	 */
	public DecidirResponse<Page> getPayments(Integer offset, Integer pageSize, String siteOperationId,
			String siteId) throws DecidirException {
		return paymentsService.getPayments(offset, pageSize, siteOperationId, siteId);
	}

	/**
	 * Retrieve payment by id
	 * @param paymentId
	 * @return a {@link DecidirResponse} with the {@link Payment} if exists
	 * @throws DecidirException if payment does not exist or an error occurs
	 * 
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #getRefunds(Long)
	 * @see #getCardTokens(String, String, String, String, String)
	 * @see #confirmPayment(Long, Long, String)
	 */
	public DecidirResponse<PaymentResponse> getPayment(Long paymentId) throws DecidirException {
		return paymentsService.getPayment(paymentId);
	}

	/**
	 *
	 * @param paymentId
	 * @return
	 * @throws DecidirException when an error occurs
	 * 
	 * @see #cancelRefund(Long, Long, String)
	 * @see #refundPayment(Long, RefundPayment, String)
	 * @see #getPayment(Long)
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #getCardTokens(String, String, String, String, String)
	 */
	public DecidirResponse<RefundPaymentHistoryResponse> getRefunds(Long paymentId) throws DecidirException {
		return refundsService.getRefunds(paymentId);
	}

	/**
	 *
	 * @param paymentId
	 * @param refundPayment
	 * @return
	 * @throws DecidirException when an error occurs
	 * 
	 * @see #cancelRefund(Long, Long, String)
	 * @see #getRefunds(Long)
	 * @see #getPayment(Long)
	 * @see #getPayments(Integer, Integer, String, String)
	 */
	public DecidirResponse<RefundPaymentResponse> refundPayment(Long paymentId, RefundPayment refundPayment, String user)
			throws RefundException, DecidirException {
		return refundsService.refundPayment(paymentId, refundPayment, user);
	}

	/**
	 *
	 * @param paymentId
	 * @param refundId
	 * @return
	 * @throws DecidirException when an error occurs
	 * 
	 * @see #getRefunds(Long)
	 * @see #refundPayment(Long, RefundPayment, String)
	 */
	public DecidirResponse<AnnulRefundResponse> cancelRefund(Long paymentId, Long refundId, String user) throws AnnulRefundException, DecidirException {
		return refundsService.cancelRefund(paymentId, refundId, user);
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
	 *             when an error occurs
	 *             
	 *             
	 * @see #getRefunds(Long)
	 * @see #getPayment(Long)
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #deleteCardToken(String)
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
	 *             when an error occurs or token does not exist
	 *             
	 * @see #getCardTokens(String, String, String, String, String)
	 */
	public DecidirResponse<Void> deleteCardToken(String token) throws DecidirException {
		return cardTokenService.deleteCardToken(token);
	}
	
	/**
	 * Confirm a pre approved payment
	 * @param paymentId
	 * @param amount (nullable) new payment amount
	 * @return @return a {@link DecidirResponse} when success
	 * @throws DecidirException when an error occurs 
	 *
	 * @see #payment(PaymentRequest)
	 * @see #payment(PaymentPciCardRequest)
	 * @see #payment(PaymentPciTokenRequest)
	 * @see #getPayment(Long) 
	 * @see #getPayments(Integer, Integer, String, String)
	 * @see #refundPayment(Long, RefundPayment, String)
	 */
	public DecidirResponse<PaymentResponse> confirmPayment(Long paymentId, Long amount, String user)
			throws PaymentException, DecidirException {
		return paymentConfirmService.paymentConfirm(paymentId, amount, user);
	}

}
