package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.ArticleResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.mapper.NewArticleMapper;
import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewArticle;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Category;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.ArticleRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.CategoryRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.UserRepository;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
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
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
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

    public Link newArticle(NewArticle newArticle) {
        Preconditions.checkNotNull(newArticle, "NewArticle object cannot be null.");

        final Category category = categoryRepository.findOneThrowable(newArticle.getCategoryId());
        final Article article = NewArticleMapper.map(newArticle);
        category.addArticle(article);
        final User user = userRepository.findOneThrowable(newArticle.getAuthorId());
        article.setAuthor(user);
        articleRepository.save(article);
        return articleResourceAssembler.linkToSingleResource(article);
    }
}
