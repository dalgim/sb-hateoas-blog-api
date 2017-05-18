package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.BlogController;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@Component
public class BlogResourceAssembler extends AbstractResourceAssembler<Blog, BlogResource, BlogController> {

    public BlogResourceAssembler(EntityLinks entityLinks) {
        super(BlogController.class, BlogResource.class, entityLinks);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        Preconditions.checkNotNull(blog, "Blog cannot be null.");

        return createResourceWithId(blog.getId(), blog);
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
