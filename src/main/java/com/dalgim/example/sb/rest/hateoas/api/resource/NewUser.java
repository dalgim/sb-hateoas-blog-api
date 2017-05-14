package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mateusz Dalgiewicz on 14.05.2017.
 */
@Getter
@Setter
public class NewUser {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
