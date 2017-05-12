package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.BlogResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.repository.BlogRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {

    private final BlogResourceAssembler blogResourceAssembler;
    private final BlogRepository blogRepository;

    public BlogResource getById(Long id) {
        Preconditions.checkNotNull(id, "Blog id cannot be null");

        final Blog blog = blogRepository.findOne(id);
        return blogResourceAssembler.toResource(blog);
    }

    public Resources<BlogResource> getAll() {
        final Iterable<Blog> allBlog = blogRepository.findAll();
        final List<BlogResource> allBlogResource = blogResourceAssembler.toResources(allBlog);
        return new Resources<>(allBlogResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }
}
