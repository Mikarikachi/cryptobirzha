package ru.larina.cryptobirzha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.larina.cryptobirzha.model.ExchangeRateRequest;
import ru.larina.cryptobirzha.model.ExchangeRateResponse;
import ru.larina.cryptobirzha.model.updateexchange.UpdateExchangeRequest;
import ru.larina.cryptobirzha.model.updateexchange.UpdateExchangeResponse;
import ru.larina.cryptobirzha.model.entity.ExchangeRate;
import ru.larina.cryptobirzha.repository.ExchangeRateRepository;

import java.util.Optional;

@Service
public class ExchangeService {
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    public UpdateExchangeResponse updateExchange(UpdateExchangeRequest request) {

        ExchangeRate currentExchangeRate = exchangeRateRepository.findById(request.getBaseCurrency()).get();

        currentExchangeRate.setBtc(request.getBTC());
        currentExchangeRate.setTon(request.getTON());
        currentExchangeRate.setRub(request.getRUB());

        exchangeRateRepository.save(currentExchangeRate);

        return new UpdateExchangeResponse(currentExchangeRate.getBtc(),
                currentExchangeRate.getTon(),
                currentExchangeRate.getRub());
    }

    public ExchangeRateResponse getExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        Optional<ExchangeRate> currency = exchangeRateRepository.findById(exchangeRateRequest.getCurrency());
        ExchangeRate exchangeRate = currency.get();
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        if (!(exchangeRate.getRub() == null)) {
            exchangeRateResponse.setRub(exchangeRate.getRub());
        }
        if (!(exchangeRate.getTon() == null)) {
            exchangeRateResponse.setTon(exchangeRate.getTon());
        }
        if (!(exchangeRate.getBtc() == null)) {
            exchangeRateResponse.setBtc(exchangeRate.getBtc());
        }
        return exchangeRateResponse;
    }
}
