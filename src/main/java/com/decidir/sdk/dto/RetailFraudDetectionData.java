package com.decidir.sdk.dto;

/**
 * Retail specific Fraud Detection Data DTO
 */
public class RetailFraudDetectionData extends FraudDetectionData {

	private ShippingData ship_to;
	private RetailTransactionData retail_transaction_data;

	public ShippingData getShip_to() {
		return ship_to;
	}

	public void setShip_to(ShippingData ship_to) {
		this.ship_to = ship_to;
	}

	public RetailTransactionData getRetail_transaction_data() {
		return retail_transaction_data;
	}

	public void setRetail_transaction_data(RetailTransactionData retail_transaction_data) {
		this.retail_transaction_data = retail_transaction_data;
	}

}
