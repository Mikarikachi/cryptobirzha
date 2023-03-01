package ru.larina.cryptobirzha.model.entity;

import com.google.common.hash.Hashing;
import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secret_key_id", referencedColumnName = "id")
    private SecretKey secretKey;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;
    private String role;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public static void setSecretKey(User user) {
        user.secretKey = new SecretKey(Hashing.sha256()
                .hashString(user.username + user.email, StandardCharsets.UTF_8)
                .toString());
    }

}
