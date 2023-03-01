package ru.larina.cryptobirzha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.larina.cryptobirzha.model.*;
import ru.larina.cryptobirzha.model.currencyexchange.CurrencyExchangeRequest;
import ru.larina.cryptobirzha.model.currencyexchange.CurrencyExchangeResponse;
import ru.larina.cryptobirzha.model.entity.SecretKey;
import ru.larina.cryptobirzha.model.entity.User;
import ru.larina.cryptobirzha.model.entity.Wallet;
import ru.larina.cryptobirzha.service.ExchangeService;
import ru.larina.cryptobirzha.service.UserService;
import ru.larina.cryptobirzha.validator.UserValidator;

import static ru.larina.cryptobirzha.model.entity.User.setSecretKey;
import static ru.larina.cryptobirzha.validator.UserValidator.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ExchangeService exchangeService;


    @PostMapping("/signUp")
    public ResponseEntity<?> generateSecretKey(@RequestBody User user) {
        if (!isValidGenerateSecretKeyRequest(user)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }

        if (userService.isUnique(user)) {
            setSecretKey(user);
            user.setRole("USER");
            userService.addUser(user);
            return new ResponseEntity<>(user.getSecretKey(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AppError("В регистрации отказано, данный пользователь уже существует."),
                HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<?> checkBalance(@RequestBody SecretKey secretKey) {
        if (!isValidCheckBalanceRequest(secretKey)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }

        Wallet wallet = userService.getWallet(secretKey);
        if (wallet == null) {
            return new ResponseEntity<>(new AppError("Пользователя с данным ключом не существует."),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @PostMapping("/replenishment")
    public ResponseEntity<?> replenishmentWallet(@RequestBody ReplenishmentWallet replenishmentWallet) {
        if (!isValidReplenishmentWalletRequest(replenishmentWallet)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }

        WalletResponse response = userService.replenishmentBalance(replenishmentWallet);
       if (response == null) {
           return new ResponseEntity<>(new AppError("Введенное значение баланса некорректно."),
                   HttpStatus.OK);
       }
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cashout")
    public ResponseEntity<?> withdrawMoney(@RequestBody MoneyWithdrawal moneyWithdrawal) {
        if (!isValidWithdrawMoneyRequest(moneyWithdrawal)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }

        WalletResponse response = userService.withdrawMoney(moneyWithdrawal);
        if (response == null) {
            return new ResponseEntity<>(new AppError("Недостаточно средств на балансе."),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/rate")
    public ResponseEntity<?> getExchangeRate(@RequestBody ExchangeRateRequest exchangeRate) {
        if (!isValidGetExchangeRateRequest(exchangeRate)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exchangeService.getExchangeRate(exchangeRate), HttpStatus.OK);
    }

    @PostMapping("/trade")
    public ResponseEntity<?> tradeCurrency(@RequestBody CurrencyExchangeRequest currencyExchangeRequest) {
        if (!isValidTradeCurrencyRequest(currencyExchangeRequest)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }

        CurrencyExchangeResponse currencyExchangeResponse = userService.currencyExchange(currencyExchangeRequest);
        if (currencyExchangeResponse == null) {
            return new ResponseEntity<>(new AppError("Недостаточно средств на балансе для обмена."),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(currencyExchangeResponse, HttpStatus.OK);
    }
}
