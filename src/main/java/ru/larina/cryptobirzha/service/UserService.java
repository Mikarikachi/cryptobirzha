package ru.larina.cryptobirzha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.larina.cryptobirzha.model.*;
import ru.larina.cryptobirzha.model.currencyexchange.CurrencyExchangeRequest;
import ru.larina.cryptobirzha.model.currencyexchange.CurrencyExchangeResponse;
import ru.larina.cryptobirzha.model.entity.ExchangeRate;
import ru.larina.cryptobirzha.model.entity.SecretKey;
import ru.larina.cryptobirzha.model.entity.User;
import ru.larina.cryptobirzha.model.entity.Wallet;
import ru.larina.cryptobirzha.repository.ExchangeRateRepository;
import ru.larina.cryptobirzha.repository.SecretKeyRepository;
import ru.larina.cryptobirzha.repository.UserRepository;
import ru.larina.cryptobirzha.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecretKeyRepository secretKeyRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    public boolean isUnique(User user) {
        if (userRepository.findUserByEmailAndUsername(user.getEmail(), user.getUsername()) == null) {
            return true;
        }
        return false;
    }

    public void addUser(User user) {
        user.setWallet(Wallet.createEmptyWallet());
        userRepository.save(user);
    }

    public Wallet getWallet(SecretKey secretKey) {
        SecretKey bySecretKey = secretKeyRepository.findBySecretKey(secretKey.getSecretKey());
        User user = userRepository.findUserBySecretKey(bySecretKey);
        if (user == null || Objects.isNull(user.getRole()) || !"USER".equals(user.getRole())) {
            return null;
        }
        return user.getWallet();
    }

    public WalletResponse replenishmentBalance(ReplenishmentWallet replenishmentWallet) {
        Wallet wallet = getWallet(replenishmentWallet.getSecretKey());
        if (wallet == null) {
            return null;
        }
        WalletResponse response = new WalletResponse();
        if ((replenishmentWallet.getTON_wallet() != null) && (replenishmentWallet.getTON_wallet().compareTo(BigDecimal.ZERO) > 0)) {
            wallet.setTON_wallet(wallet.getTON_wallet().add(replenishmentWallet.getTON_wallet()));
            response.setTON_wallet(wallet.getTON_wallet().toString());
            walletRepository.save(wallet);
            return response;
        }
        if ((replenishmentWallet.getBTC_wallet() != null) && (replenishmentWallet.getBTC_wallet().compareTo(BigDecimal.ZERO) > 0)) {
            wallet.setBTC_wallet(wallet.getBTC_wallet().add(replenishmentWallet.getBTC_wallet()));
            response.setBTC_wallet(wallet.getBTC_wallet().toString());
            walletRepository.save(wallet);
            return response;
        }
        if ((replenishmentWallet.getRUB_wallet() != null) && (replenishmentWallet.getRUB_wallet().compareTo(BigDecimal.ZERO) > 0)) {
            wallet.setRUB_wallet(wallet.getRUB_wallet().add(replenishmentWallet.getRUB_wallet()));
            response.setRUB_wallet(wallet.getRUB_wallet().toString());
            walletRepository.save(wallet);
            return response;
        }
        return null;
    }

    public WalletResponse withdrawMoney(MoneyWithdrawal moneyWithdrawal) {
        WalletResponse response = new WalletResponse();
        Wallet wallet = getWallet(moneyWithdrawal.getSecretKey());
        if (wallet == null) {
            return null;
        }

        BigDecimal countBigDecimal = new BigDecimal(moneyWithdrawal.getCount());
        String currency = moneyWithdrawal.getCurrency();
        if (currency.equals("RUB") && (wallet.getRUB_wallet().compareTo(countBigDecimal) > 0)) {
            wallet.setRUB_wallet(wallet.getRUB_wallet().subtract(countBigDecimal));
            walletRepository.save(wallet);
            response.setRUB_wallet(wallet.getRUB_wallet().toString());
            return response;
        }
        if (currency.equals("TON") && (wallet.getTON_wallet().compareTo(countBigDecimal) > 0)) {
            wallet.setTON_wallet(wallet.getTON_wallet().subtract(countBigDecimal));
            walletRepository.save(wallet);
            response.setTON_wallet(wallet.getTON_wallet().toString());
            return response;
        }
        if (currency.equals("BTC") && (wallet.getBTC_wallet().compareTo(countBigDecimal) > 0)) {
            wallet.setBTC_wallet(wallet.getBTC_wallet().subtract(countBigDecimal));
            walletRepository.save(wallet);
            response.setBTC_wallet(wallet.getBTC_wallet().toString());
            return response;
        }
        return null;
    }


    public CurrencyExchangeResponse currencyExchange(CurrencyExchangeRequest currencyExchangeRequest) {
        Wallet wallet = getWallet(currencyExchangeRequest.getSecretKey());
        if (wallet == null) {
            return null;
        }

        BigDecimal amountOfWalletFrom = getWalletByName(wallet, currencyExchangeRequest.getCurrencyFrom());
        BigDecimal amountOfWalletTo = getWalletByName(wallet, currencyExchangeRequest.getCurrencyTo());
        String amountAsString = currencyExchangeRequest.getAmount();
        BigDecimal amountBigDecimal = new BigDecimal(amountAsString);
        if(amountBigDecimal.compareTo(amountOfWalletFrom) > 0) {
            return null;
        }

        Optional<ExchangeRate> exchangeRateById = exchangeRateRepository.findById(currencyExchangeRequest.getCurrencyFrom());
        ExchangeRate currentExchangeRate = exchangeRateById.get();

        if (currencyExchangeRequest.getCurrencyTo().equals("RUB")) {
            String currentExchangeRateRub = currentExchangeRate.getRub();
            return makeExchange(currencyExchangeRequest, wallet, amountOfWalletFrom, amountOfWalletTo, amountAsString, amountBigDecimal, currentExchangeRateRub);
        }
        if (currencyExchangeRequest.getCurrencyTo().equals("BTC")) {
            String currentExchangeRateBtc = currentExchangeRate.getBtc();
            return makeExchange(currencyExchangeRequest, wallet, amountOfWalletFrom, amountOfWalletTo, amountAsString, amountBigDecimal, currentExchangeRateBtc);
        }
        if (currencyExchangeRequest.getCurrencyTo().equals("TON")) {
            String currentExchangeRateTon = currentExchangeRate.getTon();
            return makeExchange(currencyExchangeRequest, wallet, amountOfWalletFrom, amountOfWalletTo, amountAsString, amountBigDecimal, currentExchangeRateTon);
        }

        return null;

    }

    private CurrencyExchangeResponse makeExchange(CurrencyExchangeRequest currencyExchangeRequest, Wallet wallet,
                                                  BigDecimal amountOfWalletFrom, BigDecimal amountOfWalletTo, String amountAsString,
                                                  BigDecimal amountBigDecimal, String currentExchangeRateTon) {
        BigDecimal valueTo;
        valueTo = amountBigDecimal.multiply(new BigDecimal(currentExchangeRateTon));
        amountOfWalletFrom = amountOfWalletFrom.subtract(amountBigDecimal);
        amountOfWalletTo = amountOfWalletTo.add(valueTo);

        setWalletByName(wallet, currencyExchangeRequest.getCurrencyFrom(), amountOfWalletFrom);
        setWalletByName(wallet, currencyExchangeRequest.getCurrencyTo(), amountOfWalletTo);

        walletRepository.save(wallet);

        return new CurrencyExchangeResponse(currencyExchangeRequest.getCurrencyFrom(),
                currencyExchangeRequest.getCurrencyTo(),
                amountAsString,
                valueTo.toString());
    }

    private BigDecimal getWalletByName(Wallet wallet, String name) {
        if ("RUB".equals(name)) {
            return wallet.getRUB_wallet();
        }
        if ("BTC".equals(name)) {
            return wallet.getBTC_wallet();
        }
        if ("TON".equals(name)) {
            return wallet.getTON_wallet();
        }
        return null;
    }

    private void setWalletByName(Wallet wallet, String name, BigDecimal newValue) {
        if ("RUB".equals(name)) {
            wallet.setRUB_wallet(newValue);
        }
        if ("BTC".equals(name)) {
            wallet.setBTC_wallet(newValue);
        }
        if ("TON".equals(name)) {
            wallet.setTON_wallet(newValue);
        }
    }

    public boolean isAdmin(SecretKey secretKey) {
        SecretKey bySecretKey = secretKeyRepository.findBySecretKey(secretKey.getSecretKey());
        User user = userRepository.findUserBySecretKey(bySecretKey);
        if ((user == null) || (user.getRole() == null)) {
            return false;
        }
        return user.getRole().equals("ADMIN");
    }
}
