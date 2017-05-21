package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.BlogResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.assembler.UserResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.mapper.NewUserMapper;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewUser;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserResourceAssembler userResourceAssembler;
    private final BlogResourceAssembler blogResourceAssembler;

    public UserResource getById(Long id) {
        Preconditions.checkNotNull(id, "User id cannot be null.");

        final User user = userRepository.findOne(id);
        UserResource userResource = userResourceAssembler.toResource(user);
        for (Blog blog : user.getBlogSet()) {
            userResource.add(blogResourceAssembler.linkToSingleResource(blog).withRel("authored-blogs"));
        }
        return userResource;
    }

    public Resources<UserResource> getAll() {
        final Iterable<User> allUser = userRepository.findAll();
        final List<UserResource> allUserResource = userResourceAssembler.toResources(allUser);
        return new Resources<>(allUserResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }

    public Link newUser(NewUser newUser) {
        Preconditions.checkNotNull(newUser, "New user object cannot be null.");

        final User user = NewUserMapper.map(newUser);
        userRepository.save(user);
        return userResourceAssembler.linkToSingleResource(user);
    }
}
