package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.BlogResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@RequiredArgsConstructor
@ExposesResourceFor(BlogResource.class)
@RestController
@RequestMapping(value = "/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogResourceAssembler blogResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getById(@PathVariable final Long id) {
        final Blog blog = blogRepository.findOne(id);
        final BlogResource blogResource = blogResourceAssembler.toResource(blog);
        return ResponseEntity.ok(blogResource);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Resources<BlogResource>> getAll() {
        final Iterable<Blog> allBlog = blogRepository.findAll();
        final List<BlogResource> allBlogResource = blogResourceAssembler.toResources(allBlog);
        final Resources<BlogResource> resources = new Resources<>(allBlogResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
