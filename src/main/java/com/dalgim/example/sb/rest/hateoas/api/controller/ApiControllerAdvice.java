package com.dalgim.example.sb.rest.hateoas.api.controller;

import com.dalgim.example.sb.rest.hateoas.api.resource.ErrorResponse;
import com.dalgim.example.sb.rest.hateoas.persistance.exception.EntityNotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
@ControllerAdvice
public class ApiControllerAdvice {

    private static class Constants {
        static String INTERNAL_ERROR_MSG = "Internal server error";
    }

    @ExceptionHandler(EntityNotFoundRuntimeException.class)
    public ResponseEntity<ErrorResponse> entityNofFound(EntityNotFoundRuntimeException ex) {
        final ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalError(Exception ex) {
        final ErrorResponse errorResponse = new ErrorResponse(Constants.INTERNAL_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
