package ru.larina.cryptobirzha.model.currencyexchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.larina.cryptobirzha.model.entity.SecretKey;

public class CurrencyExchangeRequest {
    @JsonProperty("secret_key")
    private SecretKey secretKey;
    @JsonProperty("currency_from")
    private String currencyFrom;
    @JsonProperty("currency_to")
    private String currencyTo;
    private String amount;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public String getAmount() {
        return amount;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
