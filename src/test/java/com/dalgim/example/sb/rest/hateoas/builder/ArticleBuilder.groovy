package com.dalgim.example.sb.rest.hateoas.builder

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article
import java.time.LocalDateTime

/**
 * Created by Mateusz Dalgiewicz on 25.05.2017.
 */
class ArticleBuilder {

    static Article emptyArticle() {
        return emptyArticle('Java Blog part 1')
    }

    static Article emptyArticle(String name) {
        def article = new Article(UserBuilder.user())
        article.id = 1L
        article.createdDateTime = LocalDateTime.now()
        article.updatedDateTime = LocalDateTime.now()
        article.uuid = UUID.randomUUID().toString()
        article.setName(name)
        article.setDescription(name + ' description')
        article.setContent('Something about: ' + name)
        return article
    }
}
