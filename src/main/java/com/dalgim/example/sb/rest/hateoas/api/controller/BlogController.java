package com.dalgim.example.sb.rest.hateoas.api.controller;

import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewBlog;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewBlogComment;
import com.dalgim.example.sb.rest.hateoas.api.service.BlogService;
import com.dalgim.example.sb.rest.hateoas.api.service.CategoryService;
import com.dalgim.example.sb.rest.hateoas.api.service.CommentService;
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
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@RequiredArgsConstructor
@ExposesResourceFor(BlogResource.class)
@RestController
@RequestMapping(value = "/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController extends AbstractControllerCase {

    private final BlogService blogService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getById(@PathVariable final Long id) {
        return ResponseEntity.ok(blogService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resources<BlogResource>> getAll() {
        return ResponseEntity.ok(blogService.getAll());
    }

    @RequestMapping(value = "/{id}/categories")
    public ResponseEntity<Resources<CategoryResource>> getAllCategoriesByBlogId(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getAllByBlogId(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newBlog(@RequestBody NewBlog newBlog) {
        final HttpHeaders headers = locationHttpHeaders(blogService.newBlog(newBlog));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public ResponseEntity<Resources<CommentResource>> getAllCommentsByBlogId(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getAllByBlogId(id));
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
    public ResponseEntity<Void> newComment(@RequestBody NewBlogComment newBlogComment) {
        final HttpHeaders headers = locationHttpHeaders(blogService.addComment(newBlogComment));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
