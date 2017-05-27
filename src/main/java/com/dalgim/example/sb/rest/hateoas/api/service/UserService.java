package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.BlogResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.controller.UserController;
import com.dalgim.example.sb.rest.hateoas.api.mapper.NewUserMapper;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewUser;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewUserComment;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService extends AbstractService<User, UserResource, UserController> {

    private final UserRepository userRepository;
    private final BlogResourceAssembler blogResourceAssembler;
    private final CommentService commentService;

    public UserResource getById(Long id) {
        Preconditions.checkNotNull(id, "User id cannot be null.");

        final User user = userRepository.findOne(id);
        UserResource userResource = toResource(userRepository.findOne(id));
        for (Blog blog : user.getBlogSet()) {
            userResource.add(blogResourceAssembler.linkToSingleResource(blog).withRel("authored-blogs"));
        }
        return userResource;
    }

    public Resources<UserResource> getAll() {
        return toResources(userRepository.findAll());
    }

    public Link newUser(NewUser newUser) {
        Preconditions.checkNotNull(newUser, "New user object cannot be null.");
        final User user = NewUserMapper.map(newUser);
        userRepository.save(user);
        return resourceAssembler.linkToSingleResource(user);
    }

    public Link addComment(NewUserComment newUserComment) {
        Preconditions.checkNotNull(newUserComment, "NewUserComment cannot be null.");

        final EntityLink<Comment> commentEntityLink = commentService.newComment(newUserComment.getNewComment());
        final User user = userRepository.findOneThrowable(newUserComment.getUserId());
        user.addComment(commentEntityLink.entity());
        return commentEntityLink.link();
    }
}
