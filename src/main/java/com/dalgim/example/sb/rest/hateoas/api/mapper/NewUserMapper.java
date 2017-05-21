package com.dalgim.example.sb.rest.hateoas.api.mapper;

import com.dalgim.example.sb.rest.hateoas.api.resource.NewUser;
import com.dalgim.example.sb.rest.hateoas.persistance.entity.User;

/**
 * Created by Mateusz Dalgiewicz on 14.05.2017.
 */
public class NewUserMapper {

    public static User map(NewUser newUser) {
        if (newUser == null) {
            return null;
        }

        User user = new User();
        user.setLastName(newUser.getLastName());
        user.setFirstName(newUser.getFirstName());
        user.setLogin(newUser.getLogin());
        user.setPassword(newUser.getPassword());
        return user;
    }
}
