package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.controller.CommentController;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.CommentRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService extends AbstractService<Comment, CommentResource, CommentController> {

    private final CommentRepository commentRepository;

    public CommentResource getById(Long id) {
        Preconditions.checkNotNull(id, "Comment id cannot be null.");
        return toResource(commentRepository.findOne(id));
    }

    public Resources<CommentResource> getAll() {
        return toResources(commentRepository.findAll());
    }

    public Resources<CommentResource> getAllByUserId(Long userId) {
        Preconditions.checkNotNull(userId, "User id cannot be null.");
        return toResources(commentRepository.getAllByUserId(userId));
    }

    public Resources<CommentResource> getAllByBlogId(Long blogId) {
        Preconditions.checkNotNull(blogId, "Blog id cannot be null.");
        return toResources(commentRepository.getAllByBlogId(blogId));
    }

    public Resources<CommentResource> getAllByArticleId(Long articleId) {
        Preconditions.checkNotNull(articleId, "Article id cannot be null.");
        return toResources(commentRepository.getAllByArticleId(articleId));
    }
}
