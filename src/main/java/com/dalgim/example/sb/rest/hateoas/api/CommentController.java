package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.CommentResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 05.05.2017.
 */
@ExposesResourceFor(CommentResource.class)
@RestController
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentResourceAssembler commentResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommentResource> getById(@PathVariable final Long id) {
        final Comment comment = commentRepository.findOne(id);
        final CommentResource commentResource = commentResourceAssembler.toResource(comment);
        return ResponseEntity.ok(commentResource);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Resources<CommentResource>> getAll() {
        final Iterable<Comment> allComment = commentRepository.findAll();
        final List<CommentResource> allCommentResource = commentResourceAssembler.toResources(allComment);
        final Resources<CommentResource> commentResources = new Resources<>(allCommentResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
        return ResponseEntity.ok(commentResources);
    }
}
