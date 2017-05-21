package com.dalgim.example.sb.rest.hateoas.api.service;

import com.dalgim.example.sb.rest.hateoas.api.assembler.AbstractResourceAssembler;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.List;

/**
 * Created by Mateusz Dalgiewicz on 21.05.2017.
 */
public abstract class AbstractService<E extends AbstractEntity, T extends ResourceSupport, K> {

    protected AbstractResourceAssembler<E, T, K> resourceAssembler;

    Resources<T> toResources(Iterable<E> items) {
        final List<T> resourceItems = resourceAssembler.toResources(items);
        return new Resources<T>(resourceItems, ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel());
    }

    T toResource(E item) {
        return resourceAssembler.toResource(item);
    }

    @Autowired
    public void setResourceAssembler(AbstractResourceAssembler<E, T, K> resourceAssembler) {
        this.resourceAssembler = resourceAssembler;
    }
}
