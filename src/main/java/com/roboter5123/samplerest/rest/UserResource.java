package com.roboter5123.samplerest.rest;
import com.roboter5123.samplerest.exception.GenericConflictException;
import com.roboter5123.samplerest.exception.NoSuchUserExistsException;
import com.roboter5123.samplerest.model.User;
import com.roboter5123.samplerest.model.dto.LoginDTO;
import com.roboter5123.samplerest.model.dto.UserDTO;
import com.roboter5123.samplerest.repository.UserRepository;
import com.roboter5123.samplerest.rest.assembler.UserDTOAssembler;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
    public List<EntityModel<UserDTO>> getAll(){

       return repository.findAll().stream().map(assembler::toModel).toList();
    }

    @GetMapping(path = "/users/{userId}")
    @ResponseBody
    public EntityModel<UserDTO> getOne(@PathVariable Long userId) {

        try {

            return assembler.toModel(repository.findByUserId(userId));

        }catch (NullPointerException e){

            throw new NoSuchUserExistsException("No user with id " + userId + " exists");
        }

    }

    @PostMapping(path = "/users")
    public EntityModel<UserDTO> postOne(@RequestBody @Valid LoginDTO newUser){

        if (repository.findByeMail(newUser.getEMail()) != null){

            throw new GenericConflictException();
        }

        @Valid
        User user = new User(newUser.getEMail(), newUser.getPassword());
        user.setActivated(Boolean.FALSE);
        user = repository.save(user);

        return assembler.toModel(user);
    }
}
