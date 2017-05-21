package com.dalgim.example.sb.rest.hateoas.api.mapper;

import com.dalgim.example.sb.rest.hateoas.api.resource.NewArticle;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
public class NewArticleMapper {

    public static Article map(NewArticle newArticle) {
        if (newArticle == null) {
            return null;
        }

        Article article = new Article();
        article.setDescription(newArticle.getDescription());
        article.setName(newArticle.getName());
        article.setContent(newArticle.getContent());
        return article;
    }
}
