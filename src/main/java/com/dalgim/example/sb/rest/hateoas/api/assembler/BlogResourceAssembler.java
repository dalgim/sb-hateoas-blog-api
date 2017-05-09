package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.BlogController;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 03.05.2017.
 */
@Component
public class BlogResourceAssembler extends ResourceAssemblerSupport<Blog, BlogResource> {

    public BlogResourceAssembler() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource blogResource = createResourceWithId(blog.getId(), blog);
        blogResource.setId(blog.getId());
        blogResource.setName(blog.getName());
        blogResource.setDescription(blog.getDescription());
        blogResource.setCreatedDate(blog.getCreatedDateTime());
        blogResource.setLastUpdateDate(blog.getUpdatedDateTime());
        return blogResource;
    }
}
