package com.dalgim.example.sb.rest.hateoas.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.CategoryResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import com.dalgim.example.sb.rest.hateoas.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryResourceAssembler categoryResourceAssembler;

    public CategoryResource getByid(Long id) {
        Preconditions.checkNotNull(id, "Category id cannoe be null.");

        Category category = categoryRepository.findOne(id);
        return categoryResourceAssembler.toResource(category);
    }

    public Resources<CategoryResource> getAll() {
        final Iterable<Category> allCategory = categoryRepository.findAll();
        final List<CategoryResource> allCategoryResource = categoryResourceAssembler.toResources(allCategory);
        return new Resources<>(allCategoryResource, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }
}
