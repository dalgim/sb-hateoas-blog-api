package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Set<Comment> getAllByUserId(Long id);
    Set<Comment> getAllByBlogId(Long id);
    Set<Comment> getAllByArticleId(Long id);
}
