package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.UserController;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource userResource = createResourceWithId(user.getId(), user);
        userResource.setId(user.getId());
        userResource.setPassword(user.getPassword());
        userResource.setLogin(user.getLogin());
        userResource.setLastName(user.getLastName());
        userResource.setFirstName(user.getFirstName());
        return userResource;
    }


}
