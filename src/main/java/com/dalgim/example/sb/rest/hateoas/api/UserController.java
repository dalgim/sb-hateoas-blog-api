package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.UserResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.dalgim.example.sb.rest.hateoas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@ExposesResourceFor(UserResource.class)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserRepository userRepository;
    private final UserResourceAssembler userResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getById(@PathVariable final Long id) {
        final User user = userRepository.findOne(id);
        final UserResource userResource = userResourceAssembler.toResource(user);
        return ResponseEntity.ok(userResource);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resources<UserResource>> getAll() {
        final Iterable<User> allUser = userRepository.findAll();
        final List<UserResource> allUserResource = userResourceAssembler.toResources(allUser);
        final Resources<UserResource> resources = new Resources<>(allUserResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
