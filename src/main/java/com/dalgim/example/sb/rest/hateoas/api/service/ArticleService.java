package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.ArticleResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.entity.Article;
import com.dalgim.example.sb.rest.hateoas.repository.ArticleRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleResourceAssembler articleResourceAssembler;

    public ArticleResource getById(Long id) {
        Preconditions.checkNotNull(id, "Article id cannot be null.");

        final Article article = articleRepository.findOne(id);
        return articleResourceAssembler.toResource(article);
    }

    public Resources<ArticleResource> getAll() {
        final Iterable<Article> allArticle = articleRepository.findAll();
        final List<ArticleResource> allArticleResource = articleResourceAssembler.toResources(allArticle);
        return new Resources<>(allArticleResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }

    public Resources<ArticleResource> getAllByCategoryId(Long categoryId) {
        Preconditions.checkNotNull(categoryId, "Category id cannot be null.");

        final Set<Article> allArticles = articleRepository.getAllByCategory_Id(categoryId);
        final List<ArticleResource> allArticleResources = articleResourceAssembler.toResources(allArticles);
        return new Resources<>(allArticleResources, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }

    public Resources<ArticleResource> getAllByAuthorId(Long authorId) {
        Preconditions.checkNotNull(authorId, "Author id cannot be null.");

        final Set<Article> allArticles = articleRepository.getAllByAuthor_Id(authorId);
        final List<ArticleResource> allArticleResources = articleResourceAssembler.toResources(allArticles);
        return new Resources<>(allArticleResources, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }
}
