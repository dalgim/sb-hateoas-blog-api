package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
public class Blog extends AbstractEntity {

    @Column(unique = true)
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Category> categorySet = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Comment> commentSet = new HashSet<>();

    public void addCategory(Category category) {
        this.categorySet.add(category);
    }

    public void removeCategory(Category category) {
        this.categorySet.remove(category);
    }
}
