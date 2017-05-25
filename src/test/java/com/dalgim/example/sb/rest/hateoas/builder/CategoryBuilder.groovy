package com.dalgim.example.sb.rest.hateoas.builder

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Category
import java.time.LocalDateTime

/**
 * Created by Mateusz Dalgiewicz on 25.05.2017.
 */
class CategoryBuilder {

    static Category category(String name) {
        Category category = new Category()
        category.id = 1L
        category.createdDateTime = LocalDateTime.now()
        category.updatedDateTime = LocalDateTime.now()
        category.uuid = UUID.randomUUID().toString()
        category.setName(name)
        category.setDescription(name + ' description')
        category.addArticle(ArticleBuilder.emptyArticle('Java Blog part 1'))
        category.addArticle(ArticleBuilder.emptyArticle('Java Blog part 2'))
        category.addArticle(ArticleBuilder.emptyArticle('Java Blog part 3'))
        return category
    }

}
