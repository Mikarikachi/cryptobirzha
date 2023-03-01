package ru.larina.cryptobirzha.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal BTC_wallet;
    private BigDecimal TON_wallet;
    private BigDecimal RUB_wallet;
    @OneToOne(mappedBy = "wallet")
    private User user;

    public void setBTC_wallet(BigDecimal BTC_wallet) {
        this.BTC_wallet = BTC_wallet;
    }

    public void setTON_wallet(BigDecimal TON_wallet) {
        this.TON_wallet = TON_wallet;
    }

    public void setRUB_wallet(BigDecimal RUB_wallet) {
        this.RUB_wallet = RUB_wallet;
    }

    @JsonProperty("BTC_wallet")
    public BigDecimal getBTC_wallet() {
        return BTC_wallet;
    }

    @JsonProperty("TON_wallet")
    public BigDecimal getTON_wallet() {
        return TON_wallet;
    }

    @JsonProperty("RUB_wallet")
    public BigDecimal getRUB_wallet() {
        return RUB_wallet;
    }

    public static Wallet createEmptyWallet () {
        Wallet wallet = new Wallet();
        wallet.setBTC_wallet(BigDecimal.ZERO);
        wallet.setRUB_wallet(BigDecimal.ZERO);
        wallet.setTON_wallet(BigDecimal.ZERO);
        return wallet;
    }
}
