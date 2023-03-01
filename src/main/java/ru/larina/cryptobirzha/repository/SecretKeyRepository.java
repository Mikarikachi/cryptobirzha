package ru.larina.cryptobirzha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.larina.cryptobirzha.model.entity.SecretKey;

public interface SecretKeyRepository extends JpaRepository<SecretKey, Long> {
    SecretKey findBySecretKey(String secretKey);
}
