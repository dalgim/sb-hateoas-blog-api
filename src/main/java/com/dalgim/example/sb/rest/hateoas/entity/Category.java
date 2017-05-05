package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
public class Category extends AbstractEntity {

    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Set<Article> articleSet = new HashSet<>();

    public void addArticle(Article article) {
        this.articleSet.add(article);
    }

    public void removeArticle(Article article) {
        this.articleSet.remove(article);
    }
}