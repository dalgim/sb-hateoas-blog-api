package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.exception.EntityNotFoundRuntimeException;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
public interface UserRepository extends ThrowableRepository<User, Long> {

    User findByLogin(String login);

}
