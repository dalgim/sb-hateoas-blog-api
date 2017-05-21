package com.dalgim.example.sb.rest.hateoas.api.controller;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
abstract class AbstractControllerCase {

    HttpHeaders locationHttpHeaders(Link link) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, link.getHref());
        return httpHeaders;
    }
}
