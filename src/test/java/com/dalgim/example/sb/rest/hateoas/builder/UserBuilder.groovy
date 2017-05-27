package com.dalgim.example.sb.rest.hateoas.builder

import com.dalgim.example.sb.rest.hateoas.persistance.entity.User
import java.time.LocalDateTime

/**
 * Created by Mateusz Dalgiewicz on 25.05.2017.
 */
class UserBuilder {

    static User user() {
        def user = new User()
        user.createdDateTime = LocalDateTime.now()
        user.updatedDateTime = LocalDateTime.now()
        user.setLogin('John.Smith')
        user.setFirstName('John')
        user.setLastName('Smith')
        user.setPassword('P@ssword')
        return user
    }
}
