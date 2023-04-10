package com.roboter5123.samplerest.model;
import com.roboter5123.samplerest.exception.IfThisIsThrownThenThereIsSomethingExtremelyWrongAndYouShouldConsultGodOnYourFurtherCourseOfActionException;
import com.roboter5123.samplerest.model.dto.UserDTO;
import com.roboter5123.samplerest.repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    @Email
    private String eMail;
    @NotEmpty
    private String hashedPassword;
    @NotEmpty
    private byte[] salt;
    private Boolean activated;
    @OneToMany(mappedBy = "user")
    private List<LoginSession> loginSessions;

    public User(String eMail, String unHashedPassword) {

        this.eMail = eMail;
        this.hashPassword(unHashedPassword);
    }

    private void hashPassword(String unHashedPassword) {

        this.salt = new byte[32];
        new SecureRandom().nextBytes(this.salt);

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(this.salt);
            this.hashedPassword = new String(md.digest(unHashedPassword.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException e) {

            throw new IfThisIsThrownThenThereIsSomethingExtremelyWrongAndYouShouldConsultGodOnYourFurtherCourseOfActionException("God Help Me");
        }
    }

    public User patch(UserDTO userDTO, UserRepository repository) {

        if (repository.findByeMail(userDTO.getEMail()) != null){

            userDTO.setEMail(null);
        }

        if (userDTO.getEMail() != null) {

            this.eMail = userDTO.getEMail();
        }

        if (userDTO.getActivated()!= null){

            this.activated = userDTO.getActivated();
        }

        if (userDTO.getPassword() != null){

            this.hashPassword(userDTO.getPassword());
        }

        return this;
    }

    public boolean checkPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(this.salt);
            return this.hashedPassword.equals(new String(md.digest(password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {

            throw new IfThisIsThrownThenThereIsSomethingExtremelyWrongAndYouShouldConsultGodOnYourFurtherCourseOfActionException("God Help Me");
        }

    }
}
