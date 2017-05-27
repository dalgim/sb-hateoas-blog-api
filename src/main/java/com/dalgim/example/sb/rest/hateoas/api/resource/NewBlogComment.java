package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mateusz Dalgiewicz on 27.05.2017.
 */
@Getter
@Setter
public class NewBlogComment {

    private NewComment newComment;
    private Long blogId;
}
