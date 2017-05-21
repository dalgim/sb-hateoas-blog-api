package com.dalgim.example.sb.rest.hateoas.api.mapper;

import com.dalgim.example.sb.rest.hateoas.api.resource.NewCategory;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Category;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
public class NewCategoryMapper {

    public static Category map(NewCategory newCategory) {
        if (newCategory == null) {
            return null;
        }

        Category category = new Category();
        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());
        return category;
    }
}
