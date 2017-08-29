package com.decidir.sdk.payments;

import com.decidir.sdk.dto.*;

import java.io.Serializable;
import java.util.List;

/**
 * Payment DTO used to communicate with Decidir's Payment Service
 */
public abstract class Payment implements Serializable {

	private Long id = 0L;
	private Customer customer;
	private Currency currency;
	private Long amount;
	private int installments;
	private String site_transaction_id;
	private String bin;
	private Integer payment_method_id;
	private PaymentType payment_type; // single / distributed
	private String site_id;
	private List<SubPayment> sub_payments;
	private FraudDetectionData fraud_detection;
	private String plan_id;
	private Aggregator aggregate_data;
	private String establishment_name;
	private String email;
    private Integer bank_id;
    private String invoice_expiration;
	private String payment_mode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public Integer getBank_id() {
        return bank_id;
    }

    public void setBank_id(Integer bank_id) {
        this.bank_id = bank_id;
    }

    public String getInvoice_expiration() {
        return invoice_expiration;
    }

    public void setInvoice_expiration(String invoice_expiration) {
        this.invoice_expiration = invoice_expiration;
    }

	public String getPayment_mode() {
		return payment_mode;
	}

	protected void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
}