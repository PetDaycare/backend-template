package com.roboter5123.samplerest.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
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
}