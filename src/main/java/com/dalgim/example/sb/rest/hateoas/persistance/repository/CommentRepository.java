package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
