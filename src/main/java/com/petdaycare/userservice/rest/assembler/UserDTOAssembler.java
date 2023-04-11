package com.petdaycare.userservice.rest.assembler;
import com.petdaycare.userservice.model.User;
import com.petdaycare.userservice.model.dto.OutgoingUserDTO;
import com.petdaycare.userservice.rest.UserResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDTOAssembler implements RepresentationModelAssembler<User, EntityModel<OutgoingUserDTO>> {

    @Override
    public EntityModel<OutgoingUserDTO> toModel(User user) {

        OutgoingUserDTO userDTO = new OutgoingUserDTO(user);
        return EntityModel.of(userDTO, //
                linkTo(methodOn(UserResource.class).getOne(userDTO.getUserId())).withSelfRel(),
                linkTo(methodOn(UserResource.class).getAll()).withRel("users"));
    }
}
