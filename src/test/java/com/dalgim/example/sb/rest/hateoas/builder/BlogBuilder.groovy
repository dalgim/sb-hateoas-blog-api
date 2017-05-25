package com.dalgim.example.sb.rest.hateoas.builder

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog
import java.time.LocalDateTime

/**
 * Created by Mateusz Dalgiewicz on 25.05.2017.
 */
class BlogBuilder {

    static Blog blog(String name) {
        def blog = new Blog(UserBuilder.user())
        blog.setName(name)
        blog.setDescription(name + ' description')
        blog.id = 1L
        blog.createdDateTime = LocalDateTime.now()
        blog.updatedDateTime = LocalDateTime.now()
        blog.uuid = UUID.randomUUID().toString()
        blog.addCategory(CategoryBuilder.category('JavaCategory1'))
        blog.addCategory(CategoryBuilder.category('JavaCategory2'))
        blog.addCategory(CategoryBuilder.category('JavaCategory3'))
        return blog
    }
}
