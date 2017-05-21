package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.controller.ArticleController;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mateusz Dalgiewicz on 12.05.2017.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService extends AbstractService<Article, ArticleResource, ArticleController> {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ArticleResource getById(Long id) {
        Preconditions.checkNotNull(id, "Article id cannot be null.");
        return toResource(articleRepository.findOne(id));
    }

    public Resources<ArticleResource> getAll() {
        return toResources(articleRepository.findAll());
    }

    public Resources<ArticleResource> getAllByCategoryId(Long categoryId) {
        Preconditions.checkNotNull(categoryId, "Category id cannot be null.");
        return toResources(articleRepository.getAllByCategory_Id(categoryId));
    }

    public Resources<ArticleResource> getAllByAuthorId(Long authorId) {
        Preconditions.checkNotNull(authorId, "Author id cannot be null.");
        return toResources(articleRepository.getAllByAuthor_Id(authorId));
    }

    public Link newArticle(NewArticle newArticle) {
        Preconditions.checkNotNull(newArticle, "NewArticle object cannot be null.");
        final Category category = categoryRepository.findOneThrowable(newArticle.getCategoryId());
        final Article article = NewArticleMapper.map(newArticle);
        category.addArticle(article);
        final User user = userRepository.findOneThrowable(newArticle.getAuthorId());
        article.setAuthor(user);
        articleRepository.save(article);
        return resourceAssembler.linkToSingleResource(article);
    }
}
