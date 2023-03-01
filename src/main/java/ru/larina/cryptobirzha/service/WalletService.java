package ru.larina.cryptobirzha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.larina.cryptobirzha.model.allforcurrency.AllForCurrencyRequest;
import ru.larina.cryptobirzha.model.allforcurrency.AllForCurrencyResponse;
import ru.larina.cryptobirzha.model.entity.Wallet;
import ru.larina.cryptobirzha.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;

    public AllForCurrencyResponse getAmount(AllForCurrencyRequest request) {
        List<Wallet> allWallets = walletRepository.findAll();
        String currency = request.getCurrency();
        BigDecimal amount = ZERO;

        for (Wallet wallet : allWallets) {
            if ("RUB".equals(currency)) {
                amount = amount.add(wallet.getRUB_wallet());
            }
            if ("BTC".equals(currency)) {
                amount = amount.add(wallet.getBTC_wallet());
            }
            if ("TON".equals(currency)) {
                amount = amount.add(wallet.getTON_wallet());
            }
        }

        if ("RUB".equals(currency)) {
            return new AllForCurrencyResponse(null, null, amount.toPlainString());
        }
        if ("BTC".equals(currency)) {
            return new AllForCurrencyResponse(amount.toPlainString(), null, null);
        }
        if ("TON".equals(currency)) {
            return new AllForCurrencyResponse(null,  amount.toPlainString(), null);
        }
        return null;
    }
}
