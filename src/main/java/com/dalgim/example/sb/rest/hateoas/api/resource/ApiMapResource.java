package com.dalgim.example.sb.rest.hateoas.api.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
public class ApiMapResource extends ResourceSupport {

    private final String name;
    private final String version;
    private final String author;

    public ApiMapResource(String name, String version, String author) {
        super();
        this.name = name;
        this.version = version;
        this.author = author;
    }
}
