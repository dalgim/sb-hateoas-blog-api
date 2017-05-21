package com.dalgim.example.sb.rest.hateoas.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@Getter
@Setter
@Entity
public class Comment extends AbstractEntity {

    @Column(name = "CONTENT", nullable = false)
    @Lob
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    private Comment() {}

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }
}
