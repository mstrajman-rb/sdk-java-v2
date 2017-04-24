package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/**
 * Generic Fraud Detection Data DTO
 * Created by biandra on 21/06/16.
 */
//"fraud_detection": {
//	 "send_to_cs": false,
// "bill_to": {
//    "city": "Buenos Aires",
//    "country": "AR",
//    "customer_id": "martinid",
//    "email": "accept@decidir.com.ar",
//    "first_name": "martin",
//    "last_name": "paoletta",
//    "phone_number": "1547766329",
//    "postal_code": "1427",
//    "state": "BA",
//    "street1": "GARCIA DEL RIO 4041",
//    "ip_address": "190.210.214.252"
// },
// "purchase_totals": {
//    "currency": "ARS",
//    "amount": 2
// },
// "channel": "Web",
// "customer_in_site": {
//    "days_in_site": 243,
//    "is_guest": false,
//    "password": "abracadabra",
//    "num_of_transactions": 1,
//    "cellphone_number": "12121"
// },
// "device_unique_id": "devicefingerprintid",
// "ticketing_transaction_data": {
//    "days_to_event": 55,
//    "delivery_type": "Pick up",
//    "items": [
//       {
//          "code": "popblacksabbat2016",
//          "description": "Popular Black Sabbath 2016",
//          "name": "popblacksabbat2016ss",
//          "sku": "asas",
//          "total_amount": 20,
//          "quantity": 1,
//          "unit_price": 20
//       }
//    ]
// }
//}
@JsonSubTypes({
    @Type(value = RetailFraudDetectionData.class),
    @Type(value = TicketingFraudDetectionData.class),
    @Type(value = DigitalGoodsFraudDetectionData.class)})
public abstract class FraudDetectionDataRequest implements FraudDetectionData {

	private BillingData bill_to;
	private PurchaseTotals purchase_totals;
	private Channel channel;
	private String dispatch_method;
	private CustomerInSite customer_in_site;
	private CopyPasteCardData copy_paste_card_data;
	private Boolean send_to_cs;
	private String device_unique_id;

	public BillingData getBill_to() {
		return bill_to;
	}

	public void setBill_to(BillingData bill_to) {
		this.bill_to = bill_to;
	}

	public PurchaseTotals getPurchase_totals() {
		return purchase_totals;
	}

	public void setPurchase_totals(PurchaseTotals purchase_totals) {
		this.purchase_totals = purchase_totals;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getDispatch_method() {
		return dispatch_method;
	}

	public void setDispatch_method(String dispatch_method) {
		this.dispatch_method = dispatch_method;
	}

	public CustomerInSite getCustomer_in_site() {
		return customer_in_site;
	}

	public void setCustomer_in_site(CustomerInSite customer_in_site) {
		this.customer_in_site = customer_in_site;
	}

	public CopyPasteCardData getCopy_paste_card_data() {
		return copy_paste_card_data;
	}

	public void setCopy_paste_card_data(CopyPasteCardData copy_paste_card_data) {
		this.copy_paste_card_data = copy_paste_card_data;
	}

	public Boolean getSend_to_cs() {
		return send_to_cs;
	}

	public void setSend_to_cs(Boolean send_to_cs) {
		this.send_to_cs = send_to_cs;
	}

	public String getDevice_unique_id() {
		return device_unique_id;
	}

	public void setDevice_unique_id(String device_unique_id) {
		this.device_unique_id = device_unique_id;
	}
}
