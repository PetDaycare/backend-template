package com.roboter5123.samplerest.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roboter5123.samplerest.model.User;
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

        this.userId = user.getUserId();
        this.email = user.getEMail();
        this.activated= user.getActivated();
    }

}
