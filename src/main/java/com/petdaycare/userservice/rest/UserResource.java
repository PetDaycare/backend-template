package com.petdaycare.userservice.rest;
import com.petdaycare.userservice.exception.GenericConflictException;
import com.petdaycare.userservice.exception.NoSuchUserExistsException;
import com.petdaycare.userservice.exception.UserIsNotActivatedException;
import com.petdaycare.userservice.model.User;
import com.petdaycare.userservice.model.dto.IncomingUserDTO;
import com.petdaycare.userservice.model.dto.OutgoingUserDTO;
import com.petdaycare.userservice.repository.UserRepository;
import com.petdaycare.userservice.rest.assembler.UserDTOAssembler;
import com.petdaycare.userservice.model.dto.LoginDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private final UserRepository repository;
    private final UserDTOAssembler assembler;

    @Autowired
    public UserResource(UserRepository repository, UserDTOAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

   @GetMapping(path = "/users")
   @ResponseBody
   @ResponseStatus(HttpStatus.OK)
    public List<EntityModel<OutgoingUserDTO>> getAll(){

       return repository.findAll().stream().map(assembler::toModel).toList();
    }

    @GetMapping(path = "/users/{userId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<OutgoingUserDTO> getOne(@PathVariable Long userId) {

        try {

            return assembler.toModel(repository.findByUserId(userId));

        }catch (NullPointerException e){

            throw new NoSuchUserExistsException("No user with id " + userId + " exists");
        }

    }

    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<OutgoingUserDTO> postOne(@RequestBody @Valid LoginDTO newUser){

        if (repository.findByeMail(newUser.getEmail()) != null){

            throw new GenericConflictException();
        }

        @Valid
        User user = new User(newUser.getEmail(), newUser.getPassword());
        user.setActivated(Boolean.FALSE);
        user = repository.save(user);

        return assembler.toModel(user);
    }

    @DeleteMapping(path = "/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteOne(@PathVariable Long userId){

        User user = repository.findByUserId(userId);

        if (user != null && user.getActivated() == Boolean.FALSE){

            user.setActivated(Boolean.FALSE);
            repository.save(user);
            return;
        }

        if (user != null ){

            throw new UserIsNotActivatedException("User exists but is not activated");
        }

        throw new NoSuchUserExistsException("No such user exists");
    }

    @PatchMapping(path = "/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<OutgoingUserDTO> patchOne(@PathVariable Long userId, @RequestBody IncomingUserDTO userDTO){

        User user = repository.findByUserId(userId);

        if (user == null){

            throw new NoSuchUserExistsException("No such user exists");
        }

        return assembler.toModel(user.patch(userDTO,repository));
    }
}
