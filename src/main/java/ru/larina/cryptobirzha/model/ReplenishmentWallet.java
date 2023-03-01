package ru.larina.cryptobirzha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.larina.cryptobirzha.model.entity.SecretKey;

import java.math.BigDecimal;

public class ReplenishmentWallet {
    @JsonProperty("secret_key")
    private SecretKey secretKey;
    @JsonProperty("BTC_wallet")
    private BigDecimal BTC_wallet;
    @JsonProperty("TON_wallet")
    private BigDecimal TON_wallet;
    @JsonProperty("RUB_wallet")
    private BigDecimal RUB_wallet;

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public BigDecimal getBTC_wallet() {
        return BTC_wallet;
    }

    public BigDecimal getTON_wallet() {
        return TON_wallet;
    }

    public BigDecimal getRUB_wallet() {
        return RUB_wallet;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public void setBTC_wallet(BigDecimal BTC_wallet) {
        this.BTC_wallet = BTC_wallet;
    }

    public void setTON_wallet(BigDecimal TON_wallet) {
        this.TON_wallet = TON_wallet;
    }

    public void setRUB_wallet(BigDecimal RUB_wallet) {
        this.RUB_wallet = RUB_wallet;
    }
}
