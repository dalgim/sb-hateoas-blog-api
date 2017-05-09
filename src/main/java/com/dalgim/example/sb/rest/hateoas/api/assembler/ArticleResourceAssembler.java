package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.ArticleController;
import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.entity.Article;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 08.05.2017.
 */
@Component
public class ArticleResourceAssembler extends ResourceAssemblerSupport<Article, ArticleResource> {

    public ArticleResourceAssembler() {
        super(ArticleController.class, ArticleResource.class);
    }

    @Override
    public ArticleResource toResource(Article article) {
        ArticleResource articleResource = createResourceWithId(article.getId(), article);
        articleResource.setId(article.getId());
        articleResource.setContent(article.getContent());
        articleResource.setDescription(article.getDescription());
        articleResource.setCreatedDate(article.getCreatedDateTime());
        articleResource.setLastUpdateDate(article.getUpdatedDateTime());
        articleResource.setName(article.getName());
        articleResource.setAuthorId(article.getAuthor() != null ? article.getAuthor().getId() : null);
        articleResource.setCategoryId(article.getCategory() != null ? article.getCategory().getId() : null);
        return articleResource;
    }


}
