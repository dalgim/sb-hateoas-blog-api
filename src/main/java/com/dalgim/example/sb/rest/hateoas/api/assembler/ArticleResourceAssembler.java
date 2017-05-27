package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.controller.ArticleController;
import com.dalgim.example.sb.rest.hateoas.api.controller.BlogController;
import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Mateusz Dalgiewicz on 08.05.2017.
 */
@Component
public class ArticleResourceAssembler extends AbstractResourceAssembler<Article, ArticleResource, ArticleController> {

    public ArticleResourceAssembler(final EntityLinks entityLinks) {
        super(ArticleController.class, ArticleResource.class, entityLinks);
    }

    @Override
    public ArticleResource toResource(Article article) {
        Preconditions.checkNotNull(article, "Article cannot be null.");
        final ArticleResource articleResource = createResourceWithId(article.getId(), article);
        final Link articleCommentsList = linkTo(methodOn(ArticleController.class).getAllCommentsByArticleId(article.getId())).withRel("comments");
        articleResource.add(articleCommentsList);
        return articleResource;
    }

    @Override
    protected ArticleResource instantiateResource(Article article) {
        ArticleResource articleResource = new ArticleResource();
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
