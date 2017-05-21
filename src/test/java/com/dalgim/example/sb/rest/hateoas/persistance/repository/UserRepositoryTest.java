package com.dalgim.example.sb.rest.hateoas.persistance.repository;

import com.dalgim.example.sb.rest.hateoas.AppProfiles;
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
 * Created by Mateusz Dalgiewicz on 08.05.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles(value = AppProfiles.TEST)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Test
    public void shouldSaveUserWithComments() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        Comment comment = new Comment("Test comment", user);
        user.addComment(comment);

        userRepository.save(user);
        User userByLogin = userRepository.findByLogin("John.Smith");

        assertThat(userByLogin.getLogin()).isEqualTo(user.getLogin());
        assertThat(userByLogin.getId()).isNotNull();
        assertThat(userByLogin.getCommentSet()).size().isEqualTo(1);
        assertThat(userByLogin.getCommentSet()).containsExactly(comment);
        assertThat(userByLogin.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userByLogin.getLastName()).isEqualTo(user.getLastName());
        assertThat(userByLogin.getPassword()).isEqualTo(user.getPassword());
        userByLogin.getCommentSet()
                .forEach(comment1 -> assertThat(comment1.getAuthor()).isEqualTo(user));
    }

    @Test
    public void shouldDeleteUserAndComments() throws Exception {
        User user = new User();
        user.setLogin("John.Smith");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword("P@ssw0rd");
        Comment comment = new Comment("Test comment", user);
        user.addComment(comment);

        userRepository.save(user);
        userRepository.delete(user);

        assertThat(userRepository.findByLogin(user.getLogin())).isNull();
        user.getCommentSet()
                .forEach(comment1 -> assertThat(commentRepository.findOne(comment.getId())).isNull());
    }
}