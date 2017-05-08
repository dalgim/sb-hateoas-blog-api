package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Entity
public class Article extends AbstractEntity {

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;
    @Column(name = "CONTENT", nullable = false)
    @Lob
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ARTICLE_COMMENT",
            joinColumns = @JoinColumn(name = "ARTICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID")
    )
    private Set<Comment> commentSet = new HashSet<>();

    public void addComment(Comment comment) {
        this.commentSet.add(comment);
    }

    public void removeComment(Comment comment) {
        this.commentSet.remove(comment);
    }
}
