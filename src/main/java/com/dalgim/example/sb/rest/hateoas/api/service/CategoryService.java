package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.controller.CategoryController;
import com.dalgim.example.sb.rest.hateoas.api.mapper.NewCategoryMapper;
import com.dalgim.example.sb.rest.hateoas.api.resource.CategoryResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.NewCategory;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Category;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.BlogRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.CategoryRepository;
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
public class CategoryService extends AbstractService<Category, CategoryResource, CategoryController> {

    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;

    public CategoryResource getByid(Long id) {
        Preconditions.checkNotNull(id, "Category id cannot be null.");
        return toResource(categoryRepository.findOne(id));
    }

    public Resources<CategoryResource> getAll() {
        return toResources(categoryRepository.findAll());
    }

    public Resources<CategoryResource> getAllByBlogId(Long blogId) {
        Preconditions.checkNotNull(blogId, "Blog id cannot be null.");
        return toResources(categoryRepository.getAllByBlog_Id(blogId));
    }

    public Link newCategory(NewCategory newCategory) {
        Preconditions.checkNotNull(newCategory, "NewCategory object cannot be null.");
        final Category category = NewCategoryMapper.map(newCategory);
        final Blog blog = blogRepository.findOneThrowable(newCategory.getBlogId());
        blog.addCategory(category);
        categoryRepository.save(category);
        return resourceAssembler.linkToSingleResource(category);
    }
}
