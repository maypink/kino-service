package org.users.utils;

import org.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResourse toResource(User user){
        return new UserResourse(user.getId(), user.getUsername(), user.getName(), user.getSurname());
    }

    public User toUser(UserResourse userResourse){
        return new User(userResourse.getId(), userResourse.getUsername(), userResourse.getName(), userResourse.getSurname());
    }

}
