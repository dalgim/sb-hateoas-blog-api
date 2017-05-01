package com.dalgim.example.sb.rest.hateoas;

import com.dalgim.example.sb.rest.hateoas.entity.User;
import com.dalgim.example.sb.rest.hateoas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbRestHateoasApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
		/*User user3 = User.builder()
				.firstName("Johny")
				.lastName("White")
				.login("Johny.White")
				.password("P@ssw0rd")
				.build();*/
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Smith");
		user.setLogin("John.Smith");
		user.setPassword("P@ssw0rd");
		userRepository.save(user);

	}

}
