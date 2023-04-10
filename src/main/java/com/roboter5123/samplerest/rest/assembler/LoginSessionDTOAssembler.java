package com.roboter5123.samplerest.rest.assembler;
import com.roboter5123.samplerest.model.LoginSession;
import com.roboter5123.samplerest.model.dto.LoginSessionDTO;
import com.roboter5123.samplerest.rest.LoginSessionResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LoginSessionDTOAssembler implements RepresentationModelAssembler<LoginSession, EntityModel<LoginSessionDTO>> {

    @Override
    public EntityModel<LoginSessionDTO> toModel(LoginSession session) {

        LoginSessionDTO sessionDTO = new LoginSessionDTO(session);
        return EntityModel.of(sessionDTO,
                linkTo(methodOn(LoginSessionResource.class).getOne(sessionDTO)).withSelfRel());
    }
}
