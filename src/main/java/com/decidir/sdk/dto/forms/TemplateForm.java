package com.decidir.sdk.dto.forms;

public class TemplateForm {

    private Long id;
    private String signed_transaction_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigned_transaction_id() {
        return signed_transaction_id;
    }

    public void setSigned_transaction_id(String signed_transaction_id) {
        this.signed_transaction_id = signed_transaction_id;
    }

}
