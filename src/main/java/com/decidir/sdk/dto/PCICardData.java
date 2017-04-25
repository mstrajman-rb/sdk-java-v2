package com.decidir.sdk.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Abstract card data DTO with common values for PCI payments
 * @author ivalek
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PCICardData implements Serializable {

	private String security_code;
	private CardFraudDetectionData fraud_detection;
	
	public String getSecurity_code() {
		return security_code;
	}
	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}
	public CardFraudDetectionData getFraud_detection() {
		return fraud_detection;
	}
	public void setFraud_detection(CardFraudDetectionData fraud_detection) {
		this.fraud_detection = fraud_detection;
	}

	
}
