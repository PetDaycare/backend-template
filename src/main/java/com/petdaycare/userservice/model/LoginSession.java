package com.petdaycare.userservice.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Setter
@Getter
@Entity
@Table(name = "login_session")
public class LoginSession {

    @NotNull
    @Id
    private String loginToken;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    @NotNull
    private LocalDateTime expires;

    @NotNull
    private LocalDateTime createdOn;

    public LoginSession() {

        this.createdOn = LocalDateTime.now();
        this.expires = LocalDateTime.now().plusDays(7);
        byte[] tokenBytes = new byte[64];
        new SecureRandom().nextBytes(tokenBytes);
        this.loginToken = Base64.getEncoder().encodeToString(tokenBytes);
    }
}