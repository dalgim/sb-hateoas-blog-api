package com.dalgim.example.sb.rest.hateoas.persistance.exception;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
public class EntityNotFoundRuntimeException extends RuntimeException {

    public EntityNotFoundRuntimeException(String message) {
        super(message);
    }
}
