package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.controller.BlogController;
import com.dalgim.example.sb.rest.hateoas.api.mapper.NewBlogMapper;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewBlog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.BlogRepository;
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
public class BlogService extends AbstractService<Blog, BlogResource, BlogController> {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogResource getById(Long id) {
        Preconditions.checkNotNull(id, "Blog id cannot be null.");

        final Blog blog = blogRepository.findOneThrowable(id);
        return resourceAssembler.toResource(blog);
    }

    public Resources<BlogResource> getAll() {
        return toResources(blogRepository.findAll());
    }

    public Resources<BlogResource> getAllByOwnerId(Long ownerId) {
        Preconditions.checkNotNull(ownerId, "Owner id cannot be null.");

        return toResources(blogRepository.getAllByOwner_Id(ownerId));
    }

    public Link newBlog(NewBlog newBlog) {
        Preconditions.checkNotNull(newBlog, "NewBlog object cannot be null.");

        final User user = userRepository.findOneThrowable(newBlog.getOwnerId());
        final Blog blog = NewBlogMapper.map(newBlog);
        blog.setOwner(user);
        blogRepository.save(blog);
        return resourceAssembler.linkToSingleResource(blog);
    }
}
