package com.roboter5123.samplerest.model.dto;
import com.roboter5123.samplerest.model.User;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {


    private Long userId;

    @Email
    private String eMail;

    public UserDTO(User user) {

        this.userId = user.getUserId();
        this.eMail = user.getEMail();
    }
}
