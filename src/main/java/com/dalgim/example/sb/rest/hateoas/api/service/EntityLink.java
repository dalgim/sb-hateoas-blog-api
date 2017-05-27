package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.AbstractEntity;
import org.springframework.hateoas.Link;

/**
 * Created by Mateusz Dalgiewicz on 27.05.2017.
 */
public class EntityLink<R extends AbstractEntity> {

    private Link link;
    private R entity;

    EntityLink(R entity, Link link) {
        this.link = link;
        this.entity = entity;
    }

    public Link link() {
        return this.link;
    }

    public R entity() {
        return this.entity;
    }
}
