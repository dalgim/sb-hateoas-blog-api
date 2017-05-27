package com.dalgim.example.sb.rest.hateoas.api.mapper;

import com.dalgim.example.sb.rest.hateoas.api.resource.NewComment;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;

/**
 * Created by Mateusz Dalgiewicz on 27.05.2017.
 */
public class NewCommentMapper {

    public static Comment map(NewComment newComment) {
        if (newComment == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setContent(newComment.getContent());
        return comment;
    }
}
