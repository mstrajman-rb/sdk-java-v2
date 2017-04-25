package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by biandra on 22/09/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardTokens implements Serializable {

    private List<CardToken> tokens;

    public List<CardToken> getTokens() {
        return tokens;
    }

    public void setTokens(List<CardToken> tokens) {
        this.tokens = tokens;
    }
}
