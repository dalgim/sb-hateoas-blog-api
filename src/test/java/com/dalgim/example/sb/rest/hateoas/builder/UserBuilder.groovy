package com.dalgim.example.sb.rest.hateoas.builder

import com.dalgim.example.sb.rest.hateoas.persistance.entity.User
import java.time.LocalDateTime

/**
 * Created by Mateusz Dalgiewicz on 25.05.2017.
 */
class UserBuilder {

    static User user() {
        return new User(
                firstName: 'John',
                lastName: 'Smith',
                id: 1L,
                login: 'John.Smith',
                password: 'P@ssw0rd',
                uuid: UUID.randomUUID().toString(),
                createdDateTime: LocalDateTime.now(),
                updatedDateTime: LocalDateTime.now(),
        )
    }
}
