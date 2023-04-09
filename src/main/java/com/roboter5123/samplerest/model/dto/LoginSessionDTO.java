package com.roboter5123.samplerest.model.dto;
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
    private LocalDateTime expires;
}
