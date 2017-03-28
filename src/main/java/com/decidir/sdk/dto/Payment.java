package com.decidir.sdk.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Payment DTO used to communicate with Decidir's Payment Service
 */
public abstract class Payment implements Serializable {

	private Long id = 0L;
	private String user_id;
	private User collector;
	private String operation_type;
	private User payer;
	private Boolean binary_mode;
	private Boolean live_mode;
	private Currency currency;
	private Long amount;
	private int installments;
	private String site_transaction_id;
	private String bin;
	private Integer payment_method_id;
	private PaymentType payment_type; // single / distributed% / distributed$
	private String site_id;
	private List<SubPayment> sub_payments;
	private FraudDetectionData fraud_detection;
	private String plan_id;
	private Aggregator aggregate_data;
	private String establishment_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public User getCollector() {
		return collector;
	}

	public void setCollector(User collector) {
		this.collector = collector;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public User getPayer() {
		return payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public Boolean getBinary_mode() {
		return binary_mode;
	}

	public void setBinary_mode(Boolean binary_mode) {
		this.binary_mode = binary_mode;
	}

	public Boolean getLive_mode() {
		return live_mode;
	}

	public void setLive_mode(Boolean live_mode) {
		this.live_mode = live_mode;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getInstallments() {
		return installments;
	}

	public void setInstallments(int installments) {
		this.installments = installments;
	}

	public PaymentType getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(PaymentType payment_type) {
		this.payment_type = payment_type;
	}

	public List<SubPayment> getSub_payments() {
		return sub_payments;
	}

	public void setSub_payments(List<SubPayment> sub_payments) {
		this.sub_payments = sub_payments;
	}

	public String getSite_transaction_id() {
		return site_transaction_id;
	}

	public void setSite_transaction_id(String site_transaction_id) {
		this.site_transaction_id = site_transaction_id;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public Integer getPayment_method_id() {
		return payment_method_id;
	}

	public void setPayment_method_id(Integer payment_method_id) {
		this.payment_method_id = payment_method_id;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public FraudDetectionData getFraud_detection() {
		return fraud_detection;
	}

	public void setFraud_detection(FraudDetectionData fraud_detection) {
		this.fraud_detection = fraud_detection;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public Aggregator getAggregate_data() {
		return aggregate_data;
	}

	public void setAggregate_data(Aggregator aggregate_data) {
		this.aggregate_data = aggregate_data;
	}

	public String getEstablishment_name() {
		return establishment_name;
	}

	public void setEstablishment_name(String establishment_name) {
		this.establishment_name = establishment_name;
	}

}
