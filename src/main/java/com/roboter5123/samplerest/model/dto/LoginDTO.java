package com.roboter5123.samplerest.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    @Email
    private String eMail;

    @NotEmpty
    private String password;
}
