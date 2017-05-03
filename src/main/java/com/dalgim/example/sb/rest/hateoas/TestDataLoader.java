package com.dalgim.example.sb.rest.hateoas;

import com.dalgim.example.sb.rest.hateoas.entity.Blog;
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
        initBlogs();
    }

    private void initBlogs() {
        Blog blog = new Blog();
        blog.setName("JavaBlog");
        blog.setDescription("Blog about Java");
        blogRepository.save(blog);

        Blog blog1 = new Blog();
        blog1.setName("AngluarJS2");
        blog1.setDescription("Blog about AngularJS2");
        blogRepository.save(blog1);
    }

    private void initUsers() {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("Password");
        userRepository.save(user);

        User user1 = new User();
        user1.setLogin("Jack.White");
        user1.setFirstName("Jack");
        user1.setLastName("White");
        user1.setPassword("Password");
        userRepository.save(user1);
    }
}
