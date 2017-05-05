package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String login;
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_COMMENT",
            joinColumns = @JoinColumn(name = "USER_ID"),
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
