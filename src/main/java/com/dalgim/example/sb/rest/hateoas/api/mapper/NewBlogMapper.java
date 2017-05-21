package com.dalgim.example.sb.rest.hateoas.api.mapper;

import com.dalgim.example.sb.rest.hateoas.api.resource.NewBlog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.google.common.collect.Sets;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
public class NewBlogMapper {

    public static Blog map(NewBlog newBlog) {
        if (newBlog == null) {
            return null;
        }

        Blog blog = new Blog();
        blog.setDescription(newBlog.getDescription());
        blog.setName(newBlog.getName());
        Optional.ofNullable(newBlog.getCategoryList())
                .ifPresent(newCategories -> newCategories.stream()
                        .map(NewCategoryMapper::map)
                        .collect(Collectors.toSet())
                        .forEach(blog::addCategory)
                );
        return blog;
    }



}
