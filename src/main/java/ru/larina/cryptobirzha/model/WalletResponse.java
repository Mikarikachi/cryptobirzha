package ru.larina.cryptobirzha.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletResponse {

    private String BTC_wallet;
    private String TON_wallet;
    private String RUB_wallet;

    public void setBTC_wallet(String BTC_wallet) {
        this.BTC_wallet = BTC_wallet;
    }

    public void setTON_wallet(String TON_wallet) {
        this.TON_wallet = TON_wallet;
    }

    public void setRUB_wallet(String RUB_wallet) {
        this.RUB_wallet = RUB_wallet;
    }

    @JsonProperty("BTC_wallet")
    public String getBTC_wallet() {
        return BTC_wallet;
    }

    @JsonProperty("TON_wallet")
    public String getTON_wallet() {
        return TON_wallet;
    }

    @JsonProperty("RUB_wallet")
    public String getRUB_wallet() {
        return RUB_wallet;
    }
}
