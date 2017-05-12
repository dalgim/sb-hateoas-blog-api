package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.CommentController;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.entity.Comment;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 05.05.2017.
 */
@Component
public class CommentResourceAssembler extends ResourceAssemblerSupport<Comment, CommentResource> {

    public CommentResourceAssembler() {
        super(CommentController.class, CommentResource.class);
    }

    @Override
    public CommentResource toResource(Comment comment) {
        CommentResource commentResource = createResourceWithId(comment.getId(), comment);
        commentResource.setContent(comment.getContent());
        commentResource.setCreatedDate(comment.getCreatedDateTime());
        commentResource.setLastUpdateDate(comment.getUpdatedDateTime());
        return commentResource;
    }
}
