package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Dalgiewicz on 09.05.2017.
 */
@Getter
@Setter
@Relation(value = "category", collectionRelation = "categories")
public class CategoryResource extends ResourceSupport {

    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdateDate;
}
