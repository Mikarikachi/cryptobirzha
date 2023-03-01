package ru.larina.cryptobirzha.model.currencyexchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyExchangeResponse {
    @JsonProperty("currency_from")
    private String currencyFrom;
    @JsonProperty("currency_to")
    private String currencyTo;
    @JsonProperty("amount_from")
    private String amountFrom;
    @JsonProperty("amount_to")
    private String amountTo;

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public String getAmountFrom() {
        return amountFrom;
    }

    public String getAmountTo() {
        return amountTo;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public void setAmountFrom(String amountFrom) {
        this.amountFrom = amountFrom;
    }

    public void setAmountTo(String amountTo) {
        this.amountTo = amountTo;
    }

    public CurrencyExchangeResponse(String currencyFrom, String currencyTo, String amountFrom, String amountTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }
}
