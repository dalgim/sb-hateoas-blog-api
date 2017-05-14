package com.dalgim.example.sb.rest.hateoas.api.resource;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 14.05.2017.
 */
@Getter
@Setter
public class NewBlog {

    private String name;
    private String description;
    private Long ownerId;
    private List<NewCategory> categoryList = Lists.newArrayList();
}
