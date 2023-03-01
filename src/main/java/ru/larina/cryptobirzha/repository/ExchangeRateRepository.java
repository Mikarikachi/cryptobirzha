package ru.larina.cryptobirzha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.larina.cryptobirzha.model.entity.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, String> {
}
