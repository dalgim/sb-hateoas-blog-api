package com.dalgim.example.sb.rest.hateoas.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Comment extends AbstractEntity {

    @Column(name = "CONTENT", nullable = false)
    @Lob
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    private Comment() {};

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }
}
