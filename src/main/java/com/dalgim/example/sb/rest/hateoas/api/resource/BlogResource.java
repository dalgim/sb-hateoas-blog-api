package com.dalgim.example.sb.rest.hateoas.api.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@Getter
@Setter
@Relation(value = "blog", collectionRelation = "blogs")
public class BlogResource extends ResourceSupport {

    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdateDate;
}
