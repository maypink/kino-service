package org.users.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.users.exception.user.customException.UserDuplicateException;
import org.users.exception.user.customException.UserNotFoundException;
import org.users.service.UserService;
import org.users.utils.UserResource;

import java.util.List;

@Component
@Slf4j
public class RabbitMqListener {

    @Autowired
    UserService userService;

    Gson gson = new Gson();

    @RabbitListener(queues = "getUserByUsername")
    public String worker1(String username) {
        log.info("Accepted on worker 1 for getUserByUsername");
        List<UserResource> userResourceList = userService.findByUsername(username);
        if (userResourceList.isEmpty()){
            throw new UserNotFoundException("User with specified username does not exist.");
        }
        return gson.toJson(userResourceList.get(0));
    }

    @RabbitListener(queues = "addUser")
    public String worker2(String userResourceString) {
        log.info("Accepted on worker 1 for addUser");
        UserResource userResource = gson.fromJson(userResourceString, UserResource.class);
        List<UserResource> userResourceList = userService.findByUsername(userResource.username());
        if (!userResourceList.isEmpty()){
            throw new UserDuplicateException("Attempt to insert duplicate of user.");
        }
        UserResource savedUserResource = userService.save(userResource);
        return gson.toJson(savedUserResource);
    }
}
