package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mateusz Dalgiewicz on 14.05.2017.
 */
@Getter
@Setter
public class NewArticle {

    private String name;
    private String content;
    private String description;
    private Long categoryId;
    private Long authorId;
}
