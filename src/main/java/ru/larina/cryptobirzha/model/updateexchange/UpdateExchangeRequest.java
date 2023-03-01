package ru.larina.cryptobirzha.model.updateexchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.larina.cryptobirzha.model.entity.SecretKey;

public class UpdateExchangeRequest {
    @JsonProperty("secret_key")
    private SecretKey secretKey;
    @JsonProperty("base_currency")
    private String baseCurrency;
    @JsonProperty("BTC")
    private String BTC;
    @JsonProperty("TON")
    private String TON;
    @JsonProperty("RUB")
    private String RUB;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBTC() {
        return BTC;
    }

    public void setBTC(String BTC) {
        this.BTC = BTC;
    }

    public String getTON() {
        return TON;
    }

    public void setTON(String TON) {
        this.TON = TON;
    }

    public String getRUB() {
        return RUB;
    }

    public void setRUB(String RUB) {
        this.RUB = RUB;
    }
}
