package com.dalgim.example.sb.rest.hateoas.api.controller;

import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.api.service.ArticleService;
import com.dalgim.example.sb.rest.hateoas.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Dalgiewicz on 09.05.2017.
 */
@RequiredArgsConstructor
@ExposesResourceFor(CategoryResource.class)
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;
    private final ArticleService articleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryResource> getById(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.getByid(id));
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resources<CategoryResource>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @RequestMapping(value = "/{id}/articles")
    public ResponseEntity<Resources<ArticleResource>> getAllByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getAllByCategoryId(id));
    }
}
