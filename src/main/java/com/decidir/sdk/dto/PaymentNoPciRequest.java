package com.decidir.sdk.dto;

/**
 * DTO Payment no PCI used to communicate with Decidir's Payment Service 
 */

public class PaymentNoPciRequest extends Payment {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
