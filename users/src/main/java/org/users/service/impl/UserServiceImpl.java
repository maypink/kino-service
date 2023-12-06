package org.users.service.impl;

import org.springframework.stereotype.Service;
import org.users.exception.user.customException.UserDuplicateException;
import org.users.model.User;
import org.users.repository.UserRepository;
import org.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.users.utils.UserMapper;
import org.users.utils.UserResource;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserResource> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> userMapper.toResource(user)).toList();
    }

    @Override
    public List<UserResource> findByUsername(String username) {
        List<User> userList = userRepository.findByUsername(username);
        return userList.stream().map(user -> userMapper.toResource(user)).toList();
    }

    @Override
    public UserResource save(UserResource userResource) {
        List<UserResource> userResourceList = findByUsername(userResource.username());
        if (!userResourceList.isEmpty()){
            throw new UserDuplicateException("Attempt to insert duplicate of user.");
        }
        User savedUser = userRepository.save(userMapper.toUser(userResource));
        return userMapper.toResource(savedUser);
    }
}
