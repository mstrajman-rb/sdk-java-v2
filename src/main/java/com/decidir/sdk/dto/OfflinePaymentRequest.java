package com.decidir.sdk.dto;

/**
 * DTO Offline Payment no PCI used to communicate with Decidir's Payment Service
 * <br>
 * <br>
 * <strong>Usage example</strong>
 * <pre>
 * {@code ...
 * OfflinePaymentRequest offlinePaymentRequest = new OfflinePaymentRequest();
 * offlinePaymentRequest.setToken("f522e031-90cb-41ed-ba1f-46e813e8e789"); //offline payment token
 * //common offline payment data - i.e. see {@link OfflinePayment}
 * offlinePaymentRequest.setSite_transaction_id("0001234");
 * offlinePaymentRequest.setPayment_method_id(15); //mastercard
 * offlinePaymentRequest.setAmount(23250L);//Amount in cents: $232.50
 * offlinePaymentRequest.setCurrency(Currency.ARS);
 * offlinePaymentRequest.setCod_p1("123")
 * ...
 * }
 * </pre>
 */
public class OfflinePaymentRequest extends OfflinePayment {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
