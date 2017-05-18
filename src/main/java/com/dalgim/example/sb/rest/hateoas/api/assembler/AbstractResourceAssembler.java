package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.entity.AbstractEntity;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Mateusz Dalgiewicz on 18.05.2017.
 */
public abstract class AbstractResourceAssembler<T extends AbstractEntity, D extends ResourceSupport, K> extends ResourceAssemblerSupport<T, D> {

    protected final Class<K> controllerType;
    protected final Class<D> resourceType;
    protected final EntityLinks entityLinks;

    public AbstractResourceAssembler(Class<K> controllerType, Class<D> resourceType, EntityLinks entityLinks) {
        super(controllerType, resourceType);
        this.controllerType = controllerType;
        this.entityLinks = entityLinks;
        this.resourceType = resourceType;
    }

    public Link linkToSingleResource(T entity) {
        return entityLinks.linkToSingleResource(resourceType, String.valueOf(entity.getId()));
    }
}
