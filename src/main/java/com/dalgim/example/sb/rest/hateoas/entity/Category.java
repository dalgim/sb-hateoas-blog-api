package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Category extends AbstractEntity {

    @Column(name = "NAME", length = 80, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Article> articleSet = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    public void addArticle(Article article) {
        this.articleSet.add(article);
    }

    public void removeArticle(Article article) {
        this.articleSet.remove(article);
    }
}