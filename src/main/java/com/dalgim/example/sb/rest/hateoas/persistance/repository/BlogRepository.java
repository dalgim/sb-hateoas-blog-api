package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.exception.EntityNotFoundRuntimeException;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface BlogRepository extends ThrowableRepository<Blog, Long> {

    Set<Blog> getAllByOwner_Id(Long id);

}
