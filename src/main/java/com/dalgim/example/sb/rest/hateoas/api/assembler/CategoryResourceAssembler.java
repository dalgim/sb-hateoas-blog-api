package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.CategoryController;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 09.05.2017.
 */
@Component
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

    public CategoryResourceAssembler() {
        super(CategoryController.class, CategoryResource.class);
    }

    @Override
    public CategoryResource toResource(Category category) {
        CategoryResource categoryResource = createResourceWithId(category.getId(), category);
        categoryResource.setId(category.getId());
        categoryResource.setCreatedDate(category.getCreatedDateTime());
        categoryResource.setLastUpdateDate(category.getUpdatedDateTime());
        categoryResource.setName(category.getName());
        categoryResource.setDescription(category.getDescription());
        return categoryResource;
    }
}
