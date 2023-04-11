package com.petdaycare.userservice.rest.assembler;
import com.petdaycare.userservice.model.LoginSession;
import com.petdaycare.userservice.model.dto.LoginSessionDTO;
import com.petdaycare.userservice.rest.LoginSessionResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LoginSessionDTOAssembler implements RepresentationModelAssembler<LoginSession, EntityModel<LoginSessionDTO>> {

    @Override
    public EntityModel<LoginSessionDTO> toModel(LoginSession session) {

        LoginSessionDTO sessionDTO = new LoginSessionDTO();
        return EntityModel.of(sessionDTO,
                linkTo(methodOn(LoginSessionResource.class).getOne(sessionDTO)).withSelfRel());
    }
}
