package ru.larina.cryptobirzha.model.allforcurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.larina.cryptobirzha.model.entity.SecretKey;

public class AllForCurrencyRequest {
    @JsonProperty("secret_key")
    private SecretKey secretKey;
    private String currency;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
