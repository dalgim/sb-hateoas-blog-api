package com.dalgim.example.sb.rest.hateoas.repository;

import com.dalgim.example.sb.rest.hateoas.AppProfiles;
import com.dalgim.example.sb.rest.hateoas.entity.Article;
import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import com.dalgim.example.sb.rest.hateoas.entity.User;
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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldDeleteCategoryAndNotDeleteBlog() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        blogRepository.save(blog);
        Category category = new Category();
        category.setDescription("Test category description");
        category.setName("Test category");
        category.setBlog(blog);
        categoryRepository.save(category);

        categoryRepository.delete(category);

        assertThat(categoryRepository.findOne(category.getId())).isNull();
        assertThat(blogRepository.findOne(blog.getId())).isEqualTo(blog);
    }

    @Test
    public void shouldSaveCategoryAndArticle() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        blogRepository.save(blog);
        Category category = new Category();
        category.setDescription("Test category description");
        category.setName("Test category");
        category.setBlog(blog);
        Article article = new Article(user);
        article.setContent("Test content");
        article.setDescription("Test description");
        article.setName("Test article");
        category.addArticle(article);

        categoryRepository.save(category);

        Category categorySaved = categoryRepository.findOne(category.getId());

        assertThat(categorySaved).isEqualTo(category);
        assertThat(categorySaved.getArticleSet()).hasSize(1);
        assertThat(categorySaved.getArticleSet().contains(article)).isTrue();
    }

    @Test
    public void shouldDeleteCategoryAndArticles() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        blogRepository.save(blog);
        Category category = new Category();
        category.setDescription("Test category description");
        category.setName("Test category");
        category.setBlog(blog);
        Article article = new Article(user);
        article.setContent("Test content");
        article.setDescription("Test description");
        article.setName("Test article");
        category.addArticle(article);
        categoryRepository.save(category);

        categoryRepository.delete(category);

        assertThat(categoryRepository.findOne(category.getId())).isNull();
        assertThat(articleRepository.findAll()).isEmpty();

    }
}