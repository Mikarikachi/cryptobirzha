package ru.larina.cryptobirzha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.larina.cryptobirzha.model.*;
import ru.larina.cryptobirzha.model.allforcurrency.AllForCurrencyRequest;
import ru.larina.cryptobirzha.model.allforcurrency.AllForCurrencyResponse;
import ru.larina.cryptobirzha.model.updateexchange.UpdateExchangeRequest;
import ru.larina.cryptobirzha.model.updateexchange.UpdateExchangeResponse;
import ru.larina.cryptobirzha.service.ExchangeService;
import ru.larina.cryptobirzha.service.UserService;
import ru.larina.cryptobirzha.service.WalletService;
import ru.larina.cryptobirzha.validator.AdminValidator;

@RestController
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    WalletService walletService;

    @PostMapping("/updateExchange")
    public ResponseEntity<?> updateExchangeCurrency(@RequestBody UpdateExchangeRequest updateExchangeRequest) {
        if (!AdminValidator.isValidUpdateExchangeRequest(updateExchangeRequest)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }
        if (!userService.isAdmin(updateExchangeRequest.getSecretKey())) {
            return new ResponseEntity<>(new AppError("Доступ запрещен."),
                    HttpStatus.FORBIDDEN);
        }
        UpdateExchangeResponse response = exchangeService.updateExchange(updateExchangeRequest);
        if (response == null) {
            return new ResponseEntity<>(new AppError("Недостаточно средств на балансе для обмена."),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllForCurrency(@RequestBody AllForCurrencyRequest allForCurrencyRequest) {
        if (!AdminValidator.isValidGetAllForCurrencyRequest(allForCurrencyRequest)) {
            return new ResponseEntity<>(new AppError("Некорректный запрос"), HttpStatus.BAD_REQUEST);
        }
        if (!userService.isAdmin(allForCurrencyRequest.getSecretKey())) {
            return new ResponseEntity<>(new AppError("Доступ запрещен."),
                    HttpStatus.FORBIDDEN);
        }
        AllForCurrencyResponse response = walletService.getAmount(allForCurrencyRequest);
        if (response == null) {
            return new ResponseEntity<>(new AppError("Неверный идентификатор валюты"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
