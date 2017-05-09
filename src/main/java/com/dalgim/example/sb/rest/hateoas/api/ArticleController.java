package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.ArticleResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.entity.Article;
import com.dalgim.example.sb.rest.hateoas.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 08.05.2017.
 */
@RequiredArgsConstructor
@ExposesResourceFor(ArticleResource.class)
@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleResourceAssembler articleResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArticleResource> getById(@PathVariable final Long id) {
        final Article article = articleRepository.findOne(id);
        final ArticleResource articleResource = articleResourceAssembler.toResource(article);
        return ResponseEntity.ok(articleResource);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Resources<ArticleResource>> getAll() {
        final Iterable<Article> allArticle = articleRepository.findAll();
        final List<ArticleResource> allArticleResource = articleResourceAssembler.toResources(allArticle);
        final Resources<ArticleResource> articleResources = new Resources<>(allArticleResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
        return ResponseEntity.ok(articleResources);
    }
}
