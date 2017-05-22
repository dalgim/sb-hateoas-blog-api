package com.dalgim.example.sb.rest.hateoas.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */

@NamedQueries(value = {
        @NamedQuery(name = Comment.FIND_ALL_BY_USER_ID,
                query = "select u.commentSet from User u where u.id = ?1"),
        @NamedQuery(name = Comment.FIND_ALL_BY_BLOG_ID,
                query = "select b.commentSet from Blog b where b.id = ?1"),
        @NamedQuery(name = Comment.FIND_ALL_BY_ARTICLE_ID,
                query = "select a.commentSet from Article a where a.id = ?1")
})
@Getter
@Setter
@Entity
public class Comment extends AbstractEntity {

    public static final String FIND_ALL_BY_USER_ID = "Comment.getAllByUserId";
    public static final String FIND_ALL_BY_ARTICLE_ID = "Comment.getAllByBlogId";
    public static final String FIND_ALL_BY_BLOG_ID = "Comment.getAllByArticleId";

    @Column(name = "CONTENT", nullable = false)
    @Lob
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    public Comment() {}

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }
}
