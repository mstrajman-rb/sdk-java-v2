package com.decidir.sdk.dto;

import java.io.Serializable;

public class CopyPasteCardData implements Serializable {

	private String card_number;
	private String security_code;

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}
}
