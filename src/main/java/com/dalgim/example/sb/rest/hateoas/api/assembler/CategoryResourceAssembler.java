package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.CategoryController;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import com.google.common.base.Preconditions;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Mateusz Dalgiewicz on 09.05.2017.
 */
@Component
public class CategoryResourceAssembler extends AbstractResourceAssembler<Category, CategoryResource, CategoryController> {

    public CategoryResourceAssembler(final EntityLinks entityLinks) {
        super(CategoryController.class, CategoryResource.class, entityLinks);
    }

    @Override
    public CategoryResource toResource(Category category) {
        Preconditions.checkNotNull(category, "Category cannot be null.");

        final CategoryResource categoryResource = createResourceWithId(category.getId(), category);
        final Link categoryArticlesLink = linkTo(methodOn(CategoryController.class).getAllByCategoryId(category.getId())).withRel("articles");
        categoryResource.add(categoryArticlesLink);
        return categoryResource;
    }

    @Override
    protected CategoryResource instantiateResource(Category category) {
        CategoryResource categoryResource = new CategoryResource();
        categoryResource.setCreatedDate(category.getCreatedDateTime());
        categoryResource.setLastUpdateDate(category.getUpdatedDateTime());
        categoryResource.setName(category.getName());
        categoryResource.setDescription(category.getDescription());
        return categoryResource;
    }
}
