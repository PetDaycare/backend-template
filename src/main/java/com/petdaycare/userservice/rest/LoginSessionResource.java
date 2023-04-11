package com.petdaycare.userservice.rest;
import com.petdaycare.userservice.exception.IncorrectPasswordException;
import com.petdaycare.userservice.exception.NoSuchSessionExistsException;
import com.petdaycare.userservice.exception.NoSuchUserExistsException;
import com.petdaycare.userservice.model.LoginSession;
import com.petdaycare.userservice.model.User;
import com.petdaycare.userservice.repository.LoginSessionRepository;
import com.petdaycare.userservice.repository.UserRepository;
import com.petdaycare.userservice.rest.assembler.LoginSessionDTOAssembler;
import com.petdaycare.userservice.model.dto.LoginDTO;
import com.petdaycare.userservice.model.dto.LoginSessionDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class LoginSessionResource {

    UserRepository userRepository;
    LoginSessionRepository loginSessionRepository;

    LoginSessionDTOAssembler assembler;

    @Autowired
    public LoginSessionResource(UserRepository userRepository, LoginSessionRepository loginSessionRepository, LoginSessionDTOAssembler assembler) {

        this.userRepository = userRepository;
        this.loginSessionRepository = loginSessionRepository;
        this.assembler = assembler;
    }

    @GetMapping("/loginsessions")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<LoginSessionDTO> getOne(@RequestBody LoginSessionDTO loginSessionDTO){

        LoginSession loginSession = loginSessionRepository.findByLoginToken(loginSessionDTO.getLoginToken());

        if (loginSession == null){

            throw new NoSuchSessionExistsException("No such session exists");
        }

        if(loginSession.getExpires().isBefore(LocalDateTime.now())){

            loginSessionRepository.deleteByLoginToken(loginSession.getLoginToken());
            throw new NoSuchSessionExistsException("Session has expired");
        }

        return assembler.toModel(loginSession);
    }

    @PostMapping("/loginsessions")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<LoginSessionDTO> postOne(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response){

        User user = userRepository.findByeMail(loginDTO.getEmail());

        if (user == null){

            throw new NoSuchUserExistsException("No such User exists");
        }

        if (!user.checkPassword(loginDTO.getPassword())){

            throw new IncorrectPasswordException("The password entered was incorrect");
        }

        LoginSession loginSession = new LoginSession();
        loginSession.setUser(user);
        loginSessionRepository.save(loginSession);

        Cookie cookie = new Cookie("accesToken", loginSession.getLoginToken());
        cookie.setMaxAge(loginSession.getExpires().compareTo(LocalDateTime.now())*24*60*60);
        response.addCookie(cookie);

        return assembler.toModel(loginSession);
    }

    @DeleteMapping("/loginsessions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@RequestBody @Valid LoginSessionDTO sessionDTO){

        LoginSession session = loginSessionRepository.findByLoginToken(sessionDTO.getLoginToken());

        if (session == null){

            throw new NoSuchSessionExistsException("No such session exists");
        }

        loginSessionRepository.deleteByLoginToken(session.getLoginToken());
    }
}
