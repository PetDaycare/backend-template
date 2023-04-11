package com.petdaycare.userservice.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncomingUserDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    @Email(message = "Field email must contain an email.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String eMail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean activated;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


}
