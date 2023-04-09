package com.roboter5123.samplerest.rest.assembler;
import com.roboter5123.samplerest.model.User;
import com.roboter5123.samplerest.rest.UserResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(UserResource.class).getOne(entity.getUserId())).withSelfRel(),
                linkTo(methodOn(UserResource.class).getAll()).withRel("users"));
    }
}
