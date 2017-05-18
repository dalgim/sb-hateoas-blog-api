package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.UserController;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    protected final RelProvider relProvider;
    protected final EntityLinks entityLinks;

    public UserResourceAssembler(RelProvider relProvider, EntityLinks entityLinks) {
        super(UserController.class, UserResource.class);
        this.relProvider = relProvider;
        this.entityLinks = entityLinks;
    }

    @Override
    public UserResource toResource(User user) {
        Preconditions.checkNotNull(user, "User cannot be null.");

        return createResourceWithId(user.getId(), user);
    }

    @Override
    protected UserResource instantiateResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setPassword(user.getPassword());
        userResource.setLogin(user.getLogin());
        userResource.setLastName(user.getLastName());
        userResource.setFirstName(user.getFirstName());
        return userResource;
    }
}
