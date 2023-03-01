package ru.larina.cryptobirzha.validator;

import ru.larina.cryptobirzha.model.allforcurrency.AllForCurrencyRequest;
import ru.larina.cryptobirzha.model.updateexchange.UpdateExchangeRequest;


import static java.util.Objects.isNull;

public class AdminValidator {
    public static boolean isValidUpdateExchangeRequest(UpdateExchangeRequest request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }
        if (!"RUB".equals(request.getBaseCurrency()) && !"TON".equals(request.getBaseCurrency()) &&
                !"BTC".equals(request.getBaseCurrency())) {
            return false;
        }
        if (isNull(request.getRUB()) && isNull(request.getBTC()) && isNull(request.getTON())) {
            return false;
        }
        return true;
    }

    public static boolean isValidGetAllForCurrencyRequest(AllForCurrencyRequest request) {
        if (isNull(request) || isNull(request.getSecretKey())) {
            return false;
        }
        if (!"RUB".equals(request.getCurrency()) && !"TON".equals(request.getCurrency()) &&
                !"BTC".equals(request.getCurrency())) {
            return false;
        }
        return true;
    }

}
