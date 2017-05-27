package com.dalgim.example.sb.rest.hateoas.builder

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article

import java.time.LocalDateTime

/**
 * Created by Mateusz Dalgiewicz on 25.05.2017.
 */
class ArticleBuilder {

    static Article article() {
        return article('Java Blog part 1')
    }

    static Article article(String name) {
        def article = new Article(UserBuilder.user())
        article.createdDateTime = LocalDateTime.now()
        article.id = 1L
        article.updatedDateTime = LocalDateTime.now()
        article.setName(name)
        article.setDescription(name + ' description')
        article.setContent('Something about: ' + name)
        article.setAuthor(UserBuilder.user())
        def category = CategoryBuilder.emptyCategory(name + ' category')
        category.addArticle(article)
        return article
    }
}
