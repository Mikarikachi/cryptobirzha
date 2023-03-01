package ru.larina.cryptobirzha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.larina.cryptobirzha.model.entity.SecretKey;
import ru.larina.cryptobirzha.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndUsername(String email, String username);

    User findUserBySecretKey(SecretKey secretKey);
}
