package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.UserResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.dalgim.example.sb.rest.hateoas.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
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

    public UserResource getById(Long id) {
        Preconditions.checkNotNull(id, "User id cannot be null.");

        final User user = userRepository.findOne(id);
        return userResourceAssembler.toResource(user);
    }

    public Resources<UserResource> getAll() {
        final Iterable<User> allUser = userRepository.findAll();
        final List<UserResource> allUserResource = userResourceAssembler.toResources(allUser);
        return new Resources<>(allUserResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }
}
