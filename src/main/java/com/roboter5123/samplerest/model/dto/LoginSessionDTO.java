package com.roboter5123.samplerest.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roboter5123.samplerest.model.LoginSession;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LoginSessionDTO {

    @NotNull
    private String loginToken;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime expires;

    public LoginSessionDTO(LoginSession session) {

        this.loginToken = session.getLoginToken();
        this.userId= session.getUser().getUserId();
        this.expires = session.getExpires();
    }
}
