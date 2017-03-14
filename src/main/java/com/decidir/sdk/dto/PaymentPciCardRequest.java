package com.decidir.sdk.dto;

/**
 * DTO Payment PCI with card data used to communicate with Decidir's Payment Service 
 */

public class PaymentPciCardRequest extends Payment {

	private CardData card_data;

	public CardData getCard_data() {
		return card_data;
	}

	public void setCard_data(CardData card_data) {
		this.card_data = card_data;
	}
	
}
