package com.roboter5123.samplerest.rest;
import com.roboter5123.samplerest.model.User;
import com.roboter5123.samplerest.repository.UserRepository;
import com.roboter5123.samplerest.rest.assembler.UserAssembler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;

@Path("users")
public class UserResource {

    private final UserRepository repository;
    private final UserAssembler assembler;

    @Autowired
    public UserResource(UserRepository repository, UserAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GET
    public Response getAll(){

        List<EntityModel<User>> userCollectionModel= repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return Response.ok(userCollectionModel).build();
    }

    @GET
    @Path("{userid}")
    public Response getOne(@PathParam("userid") Long userId) {

        return Response.ok(assembler.toModel(repository.findByUserId(userId))).build();
    }
}
