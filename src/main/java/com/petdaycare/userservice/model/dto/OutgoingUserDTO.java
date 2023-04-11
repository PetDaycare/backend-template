package com.petdaycare.userservice.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.petdaycare.userservice.model.User;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutgoingUserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @Email(message = "Field email must contain an email.")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean activated;

    public OutgoingUserDTO(User user) {

        this.userId = user.getId();
        this.email = user.getEMail();
        this.activated= user.getActivated();
    }

}
