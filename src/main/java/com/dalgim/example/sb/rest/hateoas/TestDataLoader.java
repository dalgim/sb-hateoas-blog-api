package com.dalgim.example.sb.rest.hateoas;

import com.dalgim.example.sb.rest.hateoas.entity.User;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Init test data...");
        initUsers();
    }

    private void initUsers() {
        System.out.println("Init users...");

        User user1 = new User();
        user1.setLogin("John.Smith");
        user1.setFirstName("John");
        user1.setLastName("Smith");
        user1.setPassword("P@ssw0rd");
        userRepository.save(user1);

        User user2 = new User();
        user2.setLogin("Anna.Smith");
        user2.setFirstName("Anna");
        user2.setLastName("Smith");
        user2.setPassword("P@ssw0rd");
        userRepository.save(user2);

        User user3 = new User();
        user3.setLogin("Harry.Brown");
        user3.setFirstName("Harry");
        user3.setLastName("Brown");
        user3.setPassword("P@ssw0rd");
        userRepository.save(user3);
    }
}
