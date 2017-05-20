package com.dalgim.example.sb.rest.hateoas.repository;

import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface BlogRepository extends CrudRepository<Blog, Long> {

    Set<Blog> getAllByOwner_Id(Long id);
}
