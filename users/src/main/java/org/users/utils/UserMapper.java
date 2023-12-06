package org.users.utils;

import org.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResource toResource(User user){
        return new UserResource(user.getId(), user.getUsername(), user.getName(), user.getSurname());
    }

    public User toUser(UserResource userResource){
        return new User(userResource.id(), userResource.username(), userResource.name(), userResource.surname());
    }

}
