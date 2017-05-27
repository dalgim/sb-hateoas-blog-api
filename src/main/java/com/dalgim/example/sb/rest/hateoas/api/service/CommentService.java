package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.controller.CommentController;
import com.dalgim.example.sb.rest.hateoas.api.mapper.NewCommentMapper;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewComment;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.CommentRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
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
    private final UserRepository userRepository;

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

    public EntityLink<Comment> newComment(NewComment newComment) {
        Preconditions.checkNotNull(newComment, "NewComment cannot be null.");

        final Comment comment = NewCommentMapper.map(newComment);
        final User user = userRepository.findOneThrowable(newComment.getAuthorId());
        comment.setAuthor(user);
        commentRepository.save(comment);
        return entityLink(comment);
    }
}
