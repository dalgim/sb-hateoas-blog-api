package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.exception.EntityNotFoundRuntimeException;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    default User findOneThrowable(Long id) {
        final User user = findOne(id);
        if (user == null) {
            throw new EntityNotFoundRuntimeException("User with given id not found.");
        }
        return user;
    }
}
