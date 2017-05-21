package com.dalgim.example.sb.rest.hateoas.api.resource;

import com.dalgim.example.sb.rest.hateoas.api.serialize.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdDate;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime lastUpdateDate;
}
