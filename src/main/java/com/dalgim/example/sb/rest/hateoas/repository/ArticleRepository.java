package com.dalgim.example.sb.rest.hateoas.repository;

import com.dalgim.example.sb.rest.hateoas.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {

    Set<Article> getAllByCategory_Id(Long categoryId);
}
