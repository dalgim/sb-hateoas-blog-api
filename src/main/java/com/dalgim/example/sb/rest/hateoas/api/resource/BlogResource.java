package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@Getter
@Setter
@Relation(value = "blog", collectionRelation = "blogs")
public class BlogResource extends ResourceSupport {

    private String name;
    private String description;
}
