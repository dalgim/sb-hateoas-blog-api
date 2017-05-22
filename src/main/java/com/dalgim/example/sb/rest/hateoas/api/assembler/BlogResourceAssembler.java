package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.controller.BlogController;
import com.dalgim.example.sb.rest.hateoas.api.controller.UserController;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@Component
public class BlogResourceAssembler extends AbstractResourceAssembler<Blog, BlogResource, BlogController> {

    public BlogResourceAssembler(final EntityLinks entityLinks) {
        super(BlogController.class, BlogResource.class, entityLinks);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        Preconditions.checkNotNull(blog, "Blog cannot be null.");

        final BlogResource blogResource = createResourceWithId(blog.getId(), blog);
        final Link blogCategoriesList = linkTo(methodOn(BlogController.class).getAllCategoriesByBlogId(blog.getId())).withRel("categories");
        final Link blogCommentsList = linkTo(methodOn(BlogController.class).getAllCommentsByBlogId(blog.getId())).withRel("comments");
        blogResource.add(blogCategoriesList);
        blogResource.add(blogCommentsList);
        return blogResource;
    }

    @Override
    protected BlogResource instantiateResource(Blog blog) {
       BlogResource blogResource = new BlogResource();
        blogResource.setName(blog.getName());
        blogResource.setDescription(blog.getDescription());
        blogResource.setCreatedDate(blog.getCreatedDateTime());
        blogResource.setLastUpdateDate(blog.getUpdatedDateTime());
        return blogResource;
    }
}
