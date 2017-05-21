package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
@Getter
public class ErrorResponse {

    private int errorCode;
    private String message;

    public ErrorResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
