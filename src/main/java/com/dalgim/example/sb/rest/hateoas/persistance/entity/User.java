package com.dalgim.example.sb.rest.hateoas.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
public class User extends AbstractEntity {

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 150, nullable = false)
    private String lastName;
    @Column(name = "LOGIN", length = 100, nullable = false)
    private String login;
    @Column(name = "PASSWORD", length = 150, nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_COMMENT",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID")
    )
    private Set<Comment> commentSet = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Blog> blogSet = new HashSet<>();

    public void addBlog(Blog blog) {
        this.blogSet.add(blog);
    }

    public void remove(Blog blog) {
        this.blogSet.remove(blog);
    }

    public void addComment(Comment comment) {
        this.commentSet.add(comment);
    }

    public void removeComment(Comment comment) {
        this.commentSet.remove(comment);
    }
}
