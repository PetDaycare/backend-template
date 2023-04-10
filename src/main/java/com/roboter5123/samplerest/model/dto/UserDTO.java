package com.roboter5123.samplerest.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roboter5123.samplerest.model.User;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {


    private Long userId;

    @Email(message = "Field email must contain an email.")
    private String eMail;

    private Boolean activated;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public UserDTO(User user) {

        this.userId = user.getUserId();
        this.eMail = user.getEMail();
        this.activated= user.getActivated();
    }

}
