package com.decidir.sdk.dto.payments.agro;

import com.decidir.sdk.dto.payments.PaymentRequest;

import java.util.List;

public class AgroPaymentRequestNoPCI extends PaymentRequest {
    private String token_type;
    private int days_agreement;
    private List<InstallmentData> installmentList;
    private Boolean payment_method_is_agro;
    private int token_resolution_code;

    public AgroPaymentRequestNoPCI() {
        this.setPayment_mode("agro");
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getDays_agreement() {
        return days_agreement;
    }

    public void setDays_agreement(int days_agreement) {
        this.days_agreement = days_agreement;
    }

    public Boolean getPayment_method_is_agro() {
        return payment_method_is_agro;
    }

    public void setPayment_method_is_agro(Boolean payment_method_is_agro) {
        this.payment_method_is_agro = payment_method_is_agro;
    }

    public int getToken_resolution_code() {
        return token_resolution_code;
    }

    public void setToken_resolution_code(int token_resolution_code) {
        this.token_resolution_code = token_resolution_code;
    }

    public List<InstallmentData> getInstallmentList() {
        return installmentList;
    }

    public void setInstallmentList(List<InstallmentData> installmentList) {
        this.installmentList = installmentList;
    }
}
