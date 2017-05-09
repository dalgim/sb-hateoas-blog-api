package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.CategoryResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import com.dalgim.example.sb.rest.hateoas.repository.CategoryRepository;
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
 * Created by Mateusz Dalgiewicz on 09.05.2017.
 */
@RequiredArgsConstructor
@ExposesResourceFor(CategoryResource.class)
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryResourceAssembler categoryResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryResource> getById(@PathVariable final Long id) {
        final Category category = categoryRepository.findOne(id);
        final CategoryResource categoryResource = categoryResourceAssembler.toResource(category);
        return ResponseEntity.ok(categoryResource);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Resources<CategoryResource>> getAll() {
        final Iterable<Category> allCategory = categoryRepository.findAll();
        final List<CategoryResource> allCategoryResource = categoryResourceAssembler.toResources(allCategory);
        final Resources<CategoryResource> categoryResources = new Resources<>(allCategoryResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
        return ResponseEntity.ok(categoryResources);
    }
}
