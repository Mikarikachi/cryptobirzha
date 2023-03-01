package ru.larina.cryptobirzha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.larina.cryptobirzha.model.entity.SecretKey;

public class MoneyWithdrawal {
    @JsonProperty("secret_key")
    private SecretKey secretKey;
    private String currency;
    private String count;
    @JsonProperty("credit_card")
    private String creditCard;
    private String wallet;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCount() {
        return count;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getWallet() {
        return wallet;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
