package com.dalgim.example.sb.rest.hateoas.api.controller;

import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewArticle;
import com.dalgim.example.sb.rest.hateoas.api.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Dalgiewicz on 08.05.2017.
 */
@RequiredArgsConstructor
@ExposesResourceFor(ArticleResource.class)
@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController extends AbstractControllerCase {

    private final ArticleService articleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArticleResource> getById(@PathVariable final Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resources<ArticleResource>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newArticle(@RequestBody NewArticle newArticle) {
        final HttpHeaders headers = locationHttpHeaders(articleService.newArticle(newArticle));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
