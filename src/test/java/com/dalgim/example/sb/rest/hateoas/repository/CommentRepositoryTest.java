package com.dalgim.example.sb.rest.hateoas.repository;

import com.dalgim.example.sb.rest.hateoas.AppProfiles;
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
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldDeleteOnlyComment() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        userRepository.save(user);
        Comment comment = new Comment("Test comment", user);

        commentRepository.save(comment);
        commentRepository.delete(comment);

        assertThat(commentRepository.findOne(comment.getId())).isNull();
        assertThat(userRepository.findOne(user.getId())).isNotNull();

    }
}