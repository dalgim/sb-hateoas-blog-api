package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.exception.EntityNotFoundRuntimeException;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface BlogRepository extends CrudRepository<Blog, Long> {

    Set<Blog> getAllByOwner_Id(Long id);

    default Blog findOneThrowable(Long id) {
        final Blog blog = findOne(id);
        if (blog == null) {
            throw new EntityNotFoundRuntimeException("Blog by given identifier not found");
        }
        return blog;
    }
}
