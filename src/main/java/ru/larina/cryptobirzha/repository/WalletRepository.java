package ru.larina.cryptobirzha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.larina.cryptobirzha.model.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
