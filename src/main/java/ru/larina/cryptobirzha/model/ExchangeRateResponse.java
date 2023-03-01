package ru.larina.cryptobirzha.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRateResponse {
    private String rub;
    private String btc;
    private String ton;

    @JsonProperty("RUB")
    public String getRub() {
        return rub;
    }

    @JsonProperty("BTC")
    public String getBtc() {
        return btc;
    }

    @JsonProperty("TON")
    public String getTon() {
        return ton;
    }

    public void setRub(String rub) {
        this.rub = rub;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }

    public void setTon(String ton) {
        this.ton = ton;
    }
}
