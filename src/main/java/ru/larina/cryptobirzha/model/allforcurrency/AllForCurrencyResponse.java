package ru.larina.cryptobirzha.model.allforcurrency;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllForCurrencyResponse {
    private String BTC;
    private String TON;
    private String RUB;

    @JsonProperty("BTC")
    public String getBTC() {
        return BTC;
    }

    public void setBTC(String BTC) {
        this.BTC = BTC;
    }

    @JsonProperty("TON")
    public String getTON() {
        return TON;
    }

    public void setTON(String TON) {
        this.TON = TON;
    }

    @JsonProperty("RUB")
    public String getRUB() {
        return RUB;
    }

    public void setRUB(String RUB) {
        this.RUB = RUB;
    }

    public AllForCurrencyResponse(String BTC, String TON, String RUB) {
        this.BTC = BTC;
        this.TON = TON;
        this.RUB = RUB;
    }
}

