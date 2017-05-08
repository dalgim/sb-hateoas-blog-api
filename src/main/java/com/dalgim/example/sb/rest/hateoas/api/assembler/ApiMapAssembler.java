package com.dalgim.example.sb.rest.hateoas.api.assembler;

import com.dalgim.example.sb.rest.hateoas.api.resource.ApiMapResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.ArticleResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.BlogResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.CommentResource;
import com.dalgim.example.sb.rest.hateoas.api.resource.UserResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Component
public class ApiMapAssembler {

    private final RelProvider relProvider;
    private final EntityLinks entityLinks;
    private final String name;
    private final String version;
    private final String author;

    public ApiMapAssembler(RelProvider relProvider,
                           EntityLinks entityLinks,
                           @Value("${application.api.name}") String name,
                           @Value("${application.api.version}") String version,
                           @Value("${application.api.author}") String author) {
        this.relProvider = relProvider;
        this.entityLinks = entityLinks;
        this.author = author;
        this.version = version;
        this.name = name;
    }

    public ApiMapResource createApiMap() {
        final Link userControllerLink = entityLinks.linkToCollectionResource(UserResource.class)
                .withRel(relProvider.getCollectionResourceRelFor(UserResource.class));
        final Link blogControllerLink = entityLinks.linkToCollectionResource(BlogResource.class)
                .withRel(relProvider.getCollectionResourceRelFor(BlogResource.class));
        final Link commentControllerLink = entityLinks.linkToCollectionResource(CommentResource.class)
                .withRel(relProvider.getCollectionResourceRelFor(CommentResource.class));
        final Link articleControllerLink = entityLinks.linkToCollectionResource(ArticleResource.class)
                .withRel(relProvider.getCollectionResourceRelFor(ArticleResource.class));

        final ApiMapResource apiMapResource = new ApiMapResource(name, version, author);
        apiMapResource.add(blogControllerLink);
        apiMapResource.add(userControllerLink);
        apiMapResource.add(commentControllerLink);
        apiMapResource.add(articleControllerLink);
        return apiMapResource;
    }
}
