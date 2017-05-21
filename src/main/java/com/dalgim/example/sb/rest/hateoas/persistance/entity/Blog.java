package com.dalgim.example.sb.rest.hateoas.persistance.entity;

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
public class Blog extends AbstractEntity {

    @Column(name = "NAME", length = 80, nullable = false)
    private String name;
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<Category> categorySet = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    private User owner;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "BLOG_COMMENT",
            joinColumns = @JoinColumn(name = "BLOG_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID"))
    private Set<Comment> commentSet = new HashSet<>();

    public Blog() {}

    public Blog(User owner) {
        this.owner = owner;
        owner.addBlog(this);
    }

    public void addCategory(Category category) {
        this.categorySet.add(category);
        category.setBlog(this);
    }

    public void removeCategory(Category category) {
        this.categorySet.remove(category);
    }

    public void addComment(Comment comment) {
        this.commentSet.add(comment);
    }

    public void removeComment(Comment comment) {
        this.commentSet.remove(comment);
    }
}
