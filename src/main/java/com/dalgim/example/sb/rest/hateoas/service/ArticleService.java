package com.dalgim.example.sb.rest.hateoas.service;

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
}
