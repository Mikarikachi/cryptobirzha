package ru.larina.cryptobirzha.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    private String id;
    private String rub;
    private String btc;
    private String ton;

    public String getId() {
        return id;
    }

    public String getRub() {
        return rub;
    }

    public String getBtc() {
        return btc;
    }

    public String getTon() {
        return ton;
    }

    public void setRub(String rub) {
        if (rub == null) {
            return;
        }
        this.rub = rub;
    }

    public void setBtc(String btc) {
        if (btc == null) {
            return;
        }
        this.btc = btc;
    }

    public void setTon(String ton) {
        if (ton == null) {
            return;
        }
        this.ton = ton;
    }
}
