package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
public class Category extends AbstractEntity {

    private String name;
    private String description;
}
