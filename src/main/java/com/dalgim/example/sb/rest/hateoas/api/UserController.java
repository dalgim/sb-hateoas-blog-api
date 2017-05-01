package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.UserResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.dalgim.example.sb.rest.hateoas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@RequestMapping("/user")
@RestController
@ExposesResourceFor(UserResource.class)
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserResourceAssembler userResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getById(@PathVariable final Long id) {
        User user = userRepository.findOne(id);
        UserResource userResource = userResourceAssembler.toResource(user);
        return ResponseEntity.ok(userResource);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Resources<UserResource>> getAll() {
        return ResponseEntity.ok(null); //todo
    }
}
