package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
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
public class Article extends AbstractEntity {

    private String name;
    private String description;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;
    @OneToMany
    private Set<Comment> commentSet = new HashSet<>();

    public void addComment(Comment comment) {
        this.commentSet.add(comment);
    }

    public void removeComment(Comment comment) {
        this.commentSet.remove(comment);
    }
}
