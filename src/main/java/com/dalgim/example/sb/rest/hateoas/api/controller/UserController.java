package com.dalgim.example.sb.rest.hateoas.api.controller;

import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewUser;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewUserComment;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import com.dalgim.example.sb.rest.hateoas.api.service.ArticleService;
import com.dalgim.example.sb.rest.hateoas.api.service.BlogService;
import com.dalgim.example.sb.rest.hateoas.api.service.CommentService;
import com.dalgim.example.sb.rest.hateoas.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@ExposesResourceFor(UserResource.class)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractControllerCase {

    private final UserService userService;
    private final BlogService blogService;
    private final CommentService commentService;
    private final ArticleService articleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resources<UserResource>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @RequestMapping(value = "/{id}/blogs", method = RequestMethod.GET)
    public ResponseEntity<Resources<BlogResource>> getAllBlogsByOwnerId(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getAllByOwnerId(id));
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public ResponseEntity<Resources<CommentResource>> getAllCommentsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getAllByUserId(id));
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
    public ResponseEntity<Void> newComment(@RequestBody NewUserComment newUserComment) {
        final HttpHeaders headers = locationHttpHeaders(userService.addComment(newUserComment));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/articles", method = RequestMethod.GET)
    public ResponseEntity<Resources<ArticleResource>> getAllArticlesByAuthorId(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getAllByAuthorId(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newUser(@RequestBody NewUser newUser) {
        final HttpHeaders headers = locationHttpHeaders(userService.newUser(newUser));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
