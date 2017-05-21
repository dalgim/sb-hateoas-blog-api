package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.controller.CommentController;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 05.05.2017.
 */
@Component
public class CommentResourceAssembler extends AbstractResourceAssembler<Comment, CommentResource, CommentController> {

    public CommentResourceAssembler(final EntityLinks entityLinks) {
        super(CommentController.class, CommentResource.class, entityLinks);
    }

    @Override
    public CommentResource toResource(Comment comment) {
        Preconditions.checkNotNull(comment, "Comment cannot be null");

        return createResourceWithId(comment.getId(), comment);
    }

    @Override
    protected CommentResource instantiateResource(Comment comment) {
        CommentResource commentResource = new CommentResource();
        commentResource.setContent(comment.getContent());
        commentResource.setCreatedDate(comment.getCreatedDateTime());
        commentResource.setLastUpdateDate(comment.getUpdatedDateTime());
        return commentResource;
    }
}
