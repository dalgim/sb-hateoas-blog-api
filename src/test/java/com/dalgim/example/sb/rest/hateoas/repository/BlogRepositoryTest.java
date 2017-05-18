package com.dalgim.example.sb.rest.hateoas.repository;

import com.dalgim.example.sb.rest.hateoas.AppProfiles;
import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import com.dalgim.example.sb.rest.hateoas.entity.Comment;
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
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void shouldDeleteBlogAndNotDeleteOwner() throws Exception {
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

        blogRepository.delete(blog);

        assertThat(blogRepository.findOne(blog.getId())).isNull();
        assertThat(userRepository.findOne(user.getId())).isNotNull();
    }

    @Test
    public void shouldSaveBlogWithCategory() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        Category category = new Category();
        category.setDescription("Example category");
        category.setName("TestCategory");
        blog.addCategory(category);

        blogRepository.save(blog);

        Blog blogSaved = blogRepository.findOne(blog.getId());
        assertThat(blogSaved).isEqualTo(blog);
        assertThat(blogSaved.getCategorySet()).hasSize(1);

        Category categorySaved = categoryRepository.findOne(category.getId());
        assertThat(categorySaved).isEqualTo(category);
        assertThat(categorySaved.getBlog()).isEqualTo(blogSaved);

    }

    @Test
    public void shouldDeleteBlogAndCategories() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        Category category = new Category();
        category.setDescription("Example category");
        category.setName("TestCategory");
        blog.addCategory(category);
        Category category1 = new Category();
        category1.setDescription("Example category1");
        category1.setName("TestCategory1");
        blog.addCategory(category1);
        blogRepository.save(blog);

        user.remove(blog);
        blogRepository.delete(blog);

        assertThat(blogRepository.findOne(blog.getId())).isNull();
        assertThat(categoryRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldSaveBlogAndComment() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        Comment comment = new Comment("TestContent", user);
        blog.addComment(comment);

        blogRepository.save(blog);

        Blog blogSaved = blogRepository.findOne(blog.getId());

        assertThat(blogSaved).isEqualTo(blog);
        assertThat(blogSaved.getCommentSet()).hasSize(1);
    }

    @Test
    public void shouldDeleteBlogAndComment() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Blog blog = new Blog(user);
        blog.setName("JavaTech");
        blog.setDescription("Examples of Java");
        Comment comment = new Comment("TestContent", user);
        blog.addComment(comment);
        blogRepository.save(blog);
        user.remove(blog);
        blogRepository.delete(blog);

        assertThat(blogRepository.findOne(blog.getId())).isNull();
        assertThat(commentRepository.findAll()).isEmpty();
    }
}