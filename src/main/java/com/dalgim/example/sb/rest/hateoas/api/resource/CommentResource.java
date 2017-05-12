package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;

/**
 * Created by Mateusz Dalgiewicz on 05.05.2017.
 */
@Getter
@Setter
@Relation(value = "comment", collectionRelation = "comments")
public class CommentResource extends ResourceSupport {

    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdateDate;
}
