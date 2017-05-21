package com.dalgim.example.sb.rest.hateoas;

import com.dalgim.example.sb.rest.hateoas.entity.Blog;
import com.dalgim.example.sb.rest.hateoas.entity.Category;
import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.dalgim.example.sb.rest.hateoas.repository.BlogRepository;
import com.dalgim.example.sb.rest.hateoas.repository.UserRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Init test data...");
        initUsers();
    }

    private void initUsers() {
        System.out.println("Init users...");

        User user1 = createUser("John", "Smith");
        userRepository.save(user1);
        Blog blog1 = createBlog(user1, "JavaBlog1");
        blog1.addCategory(createCategory("JavaCat1"));
        blog1.addCategory(createCategory("JavaCat2"));
        blog1.addCategory(createCategory("JavaCat3"));
        blogRepository.save(blog1);
        Blog blog2 = createBlog(user1, "JavaBlog2");
        blogRepository.save(blog2);

        User user2 = createUser("Anna", "Smith");
        userRepository.save(user2);
        Blog blog3 = createBlog(user2, "AngularBlog1");
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
}
