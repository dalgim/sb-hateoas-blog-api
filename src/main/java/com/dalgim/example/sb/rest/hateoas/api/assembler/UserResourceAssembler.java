package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.controller.UserController;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

        final UserResource userResource = createResourceWithId(user.getId(), user);
        final Link userBlogsList = linkTo(methodOn(UserController.class).getAllBlogsByOwnerId(user.getId())).withRel("blogs");
        final Link userCommentsList = linkTo(methodOn(UserController.class).getAllCommentsByUserId(user.getId())).withRel("comments");
        final Link userArticlesList = linkTo(methodOn(UserController.class).getAllArticlesByAuthorId(user.getId())).withRel("articles");
        userResource.add(userBlogsList);
        userResource.add(userCommentsList);
        userResource.add(userArticlesList);
        return userResource;
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
