package com.decidir.sdk.dto;

/**
 * DTO Payment with token data used to communicate with Decidir's Payment Service 
 */
public class PaymentPciTokenRequest extends Payment {

	private CardTokenData card_token_data;


	public CardTokenData getCard_token_data() {
		return card_token_data;
	}

	public void setCard_token_data(CardTokenData card_token_data) {
		this.card_token_data = card_token_data;
	}
	
}
