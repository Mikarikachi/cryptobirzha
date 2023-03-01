package ru.larina.cryptobirzha.validator;

import ru.larina.cryptobirzha.model.ExchangeRateRequest;
import ru.larina.cryptobirzha.model.MoneyWithdrawal;
import ru.larina.cryptobirzha.model.ReplenishmentWallet;
import ru.larina.cryptobirzha.model.currencyexchange.CurrencyExchangeRequest;
import ru.larina.cryptobirzha.model.entity.SecretKey;
import ru.larina.cryptobirzha.model.entity.User;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.isNull;

public class UserValidator {
    public static boolean isValidGenerateSecretKeyRequest(User request) {
        if (isNull(request) || isNull(request.getEmail()) || isNull(request.getUsername())) {
            return false;
        }
        return true;
    }

    public static boolean isValidCheckBalanceRequest(SecretKey request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }
        return true;
    }

    public static boolean isValidReplenishmentWalletRequest(ReplenishmentWallet request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }
        if (isNull(request.getBTC_wallet()) && isNull(request.getRUB_wallet())
                && isNull(request.getTON_wallet())) {
            return false;
        }
        return true;
    }

    public static boolean isValidWithdrawMoneyRequest(MoneyWithdrawal request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }

        if (isNull(request.getCount()) || new BigDecimal(request.getCount()).compareTo(ZERO) <= 0) {
            return false;
        }
        if (!"RUB".equals(request.getCurrency()) && !"TON".equals(request.getCurrency()) &&
                !"BTC".equals(request.getCurrency())) {
            return false;
        }
        return true;
    }

    public static boolean isValidGetExchangeRateRequest(ExchangeRateRequest request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }
        if (!"RUB".equals(request.getCurrency()) && !"TON".equals(request.getCurrency()) &&
                !"BTC".equals(request.getCurrency())) {
            return false;
        }
        return true;
    }

    public static boolean isValidTradeCurrencyRequest(CurrencyExchangeRequest request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }
        if (!"RUB".equals(request.getCurrencyFrom()) && !"TON".equals(request.getCurrencyFrom()) &&
                !"BTC".equals(request.getCurrencyFrom())) {
            return false;
        }
        if (!"RUB".equals(request.getCurrencyTo()) && !"TON".equals(request.getCurrencyTo()) &&
                !"BTC".equals(request.getCurrencyTo())) {
            return false;
        }
        if (isNull(request.getAmount()) || new BigDecimal(request.getAmount()).compareTo(ZERO) <= 0) {
            return false;
        }
        return true;
    }

}
