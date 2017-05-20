package com.dalgim.example.sb.rest.hateoas.repository;

import com.dalgim.example.sb.rest.hateoas.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Set<Category> getAllByBlog_Id(Long id);
}
