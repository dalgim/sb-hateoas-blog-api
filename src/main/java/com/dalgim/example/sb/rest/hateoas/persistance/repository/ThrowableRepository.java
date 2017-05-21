package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.AbstractEntity;
import com.dalgim.example.sb.rest.hateoas.persistance.exception.EntityNotFoundRuntimeException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
public interface ThrowableRepository<T extends AbstractEntity, K extends Serializable> extends CrudRepository<T, K> {

    default T findOneThrowable(K key) {
        final T entity = findOne(key);
        if (entity == null) {
            throw new EntityNotFoundRuntimeException("Entity not found.");
        }
        return entity;
    }
}
