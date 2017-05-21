package com.dalgim.example.sb.rest.hateoas;

import com.dalgim.example.sb.rest.hateoas.persistance.entity.Article;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Category;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.Comment;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.ArticleRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.BlogRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.CategoryRepository;
import com.dalgim.example.sb.rest.hateoas.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@ConditionalOnProperty("initTestData")
@Component
@RequiredArgsConstructor
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Init test data...");
        clearData();
        initUsers();
    }

    private void clearData() {
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
        blogRepository.deleteAll();
        userRepository.deleteAll();
    }

    private void initUsers() {
        System.out.println("Init users...");

        User user1 = createUser("John", "Smith");
        userRepository.save(user1);
        Blog blog1 = createBlog(user1, "JavaBlog1");
        final Category javaCat1 = createCategory("JavaCat1");
        final Article article1 = createArticle(user1, "Article1");
        javaCat1.addArticle(article1);
        final Article article2 = createArticle(user1, "Article2");
        javaCat1.addArticle(article2);
        final Article article3 = createArticle(user1, "Article3");
        javaCat1.addArticle(article3);
        blog1.addCategory(javaCat1);
        final Category javaCat2 = createCategory("JavaCat2");
        blog1.addCategory(javaCat2);
        final Category javaCat3 = createCategory("JavaCat3");
        blog1.addCategory(javaCat3);

        blog1.addComment(new Comment("test", user1));
        blog1.addComment(new Comment("test2", user1));
        blog1.addComment(new Comment("test3", user1));
        blogRepository.save(blog1);
        Blog blog2 = createBlog(user1, "JavaBlog2");
        final Category java1 = createCategory("Java1");
        blog2.addCategory(java1);
        final Category java2 = createCategory("Java2");
        blog2.addCategory(java2);
        blogRepository.save(blog2);

        User user2 = createUser("Anna", "Smith");
        userRepository.save(user2);
        Blog blog3 = createBlog(user2, "AngularBlog1");
        final Category angular1 = createCategory("Angular1");
        blog3.addCategory(angular1);
        final Category angular2 = createCategory("Angular2");
        blog3.addCategory(angular2);
        final Category angular3 = createCategory("Angular3");
        blog3.addCategory(angular3);
        blogRepository.save(blog3);

        User user3 = createUser("Harry", "Brown");
        userRepository.save(user3);
    }

    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(firstName + "." + lastName);
        user.setPassword("P@ssw0rd");
        return user;
    }

    private Blog createBlog(User owner, String name) {
        Blog blog = new Blog(owner);
        blog.setName(name);
        blog.setDescription(name + " description");
        return blog;
    }

    private Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(name + " description");
        return category;
    }

    private Article createArticle(User author, String name) {
        Article article = new Article(author);
        article.setName(name);
        article.setContent(name + " content");
        article.setDescription(name + " description");
        return article;
    }

}
