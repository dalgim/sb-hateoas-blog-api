package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String login;
    private String password;

}
