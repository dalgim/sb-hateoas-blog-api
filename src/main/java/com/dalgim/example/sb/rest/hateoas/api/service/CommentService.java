package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.CommentResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.repository.CommentRepository;
import com.dalgim.example.sb.rest.hateoas.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentResourceAssembler commentResourceAssembler;

    public CommentResource getById(Long id) {
        Preconditions.checkNotNull(id, "Comment id cannot be null.");

        final Comment comment = commentRepository.findOne(id);
        return commentResourceAssembler.toResource(comment);
    }

    public Resources<CommentResource> getAll() {
        final Iterable<Comment> allComment = commentRepository.findAll();
        final List<CommentResource> allCommentResource = commentResourceAssembler.toResources(allComment);
        return new Resources<>(allCommentResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }

    public Resources<CommentResource> getAllByUserId(Long userId) {
        Preconditions.checkNotNull(userId, "User id cannot be null.");

        final Set<Comment> allCommentsByUserId = userRepository.findOne(userId).getCommentSet();
        final List<CommentResource> commentResources = commentResourceAssembler.toResources(allCommentsByUserId);
        return new Resources<>(commentResources, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }
}
