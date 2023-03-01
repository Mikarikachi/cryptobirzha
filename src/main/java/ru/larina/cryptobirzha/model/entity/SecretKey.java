package ru.larina.cryptobirzha.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table(name = "secret_key")
public class SecretKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("secret_key")
    private String secretKey;
    @OneToOne (mappedBy = "secretKey")
    private User user;

    public SecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public SecretKey() {

    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
