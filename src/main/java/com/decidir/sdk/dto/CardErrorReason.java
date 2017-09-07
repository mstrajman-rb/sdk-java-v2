package com.decidir.sdk.dto;

import java.io.Serializable;

/**
 * Created by biandra on 07/09/17.
 */
public class CardErrorReason implements Serializable{

    private Integer id;
    private String description;
    private String aditional_description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAditional_description() {
        return aditional_description;
    }

    public void setAditional_description(String aditional_description) {
        this.aditional_description = aditional_description;
    }
}
