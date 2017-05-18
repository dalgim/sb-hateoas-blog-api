package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.UserController;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Component
public class UserResourceAssembler extends AbstractResourceAssembler<User, UserResource, UserController>{

    public UserResourceAssembler(final EntityLinks entityLinks) {
        super(UserController.class, UserResource.class, entityLinks);
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
