package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.AppProfiles;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Category;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz Dalgiewicz on 09.05.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles(value = AppProfiles.TEST)
public class ArticleRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldSaveArticleAndComment() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Article article = new Article(user);
        article.setContent("Test content");
        article.setDescription("Test description");
        article.setName("Test article");
        Comment comment = new Comment("TestContent", user);
        article.addComment(comment);

        articleRepository.save(article);

        Article articleSaved = articleRepository.findOne(article.getId());

        assertThat(articleSaved).isEqualTo(article);
        assertThat(articleSaved.getCommentSet()).hasSize(1);
        assertThat(articleSaved.getCommentSet()).contains(comment);
        assertThat(commentRepository.findAll()).hasSize(1).contains(comment);
    }

    @Test
    public void shouldDeleteArticleAndComment() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Article article = new Article(user);
        article.setContent("Test content");
        article.setDescription("Test description");
        article.setName("Test article");
        Comment comment = new Comment("TestContent", user);
        article.addComment(comment);
        articleRepository.save(article);

        articleRepository.delete(article);

        assertThat(articleRepository.findOne(article.getId())).isNull();
        assertThat(commentRepository.findOne(comment.getId())).isNull();
        assertThat(commentRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldDeleteArticleAndNotDeleteOwner() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Article article = new Article(user);
        article.setContent("Test content");
        article.setDescription("Test description");
        article.setName("Test article");
        articleRepository.save(article);

        articleRepository.delete(article);

        assertThat(articleRepository.findOne(article.getId())).isNull();
        assertThat(userRepository.findOne(user.getId())).isEqualTo(user);
    }

    @Test
    public void shouldDeleteArticleAndNotDeleteCategory() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Category category = new Category();
        category.setDescription("Test category description");
        category.setName("Test category");
        categoryRepository.save(category);
        Article article = new Article(user);
        article.setContent("Test content");
        article.setDescription("Test description");
        article.setName("Test article");
        article.setCategory(category);
        articleRepository.save(article);

        articleRepository.delete(article);

        assertThat(articleRepository.findOne(article.getId())).isNull();
        assertThat(categoryRepository.findOne(category.getId())).isEqualTo(category);

    }
}