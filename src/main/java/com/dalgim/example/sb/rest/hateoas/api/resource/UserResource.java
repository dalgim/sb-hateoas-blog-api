package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Relation(value = "user", collectionRelation = "users")
public class UserResource extends ResourceSupport {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
