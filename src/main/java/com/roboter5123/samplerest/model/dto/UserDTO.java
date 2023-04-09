package com.roboter5123.samplerest.model.dto;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {


    private Long userId;

    @Email
    private String eMail;
}
