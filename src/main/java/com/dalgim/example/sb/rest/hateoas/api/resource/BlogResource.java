package com.dalgim.example.sb.rest.hateoas.api.resource;

import com.dalgim.example.sb.rest.hateoas.api.serialize.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdDate;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime lastUpdateDate;
}
