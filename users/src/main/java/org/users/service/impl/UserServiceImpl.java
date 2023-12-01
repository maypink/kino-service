package org.users.service.impl;

import org.springframework.stereotype.Service;
import org.users.exception.user.customException.UserDuplicateException;
import org.users.model.User;
import org.users.repository.UserRepository;
import org.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.users.utils.UserMapper;
import org.users.utils.UserResourse;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserResourse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> userMapper.toResource(user)).toList();
    }

    @Override
    public List<UserResourse> findByUsername(String username) {
        List<User> userList = userRepository.findByUsername(username);
        return userList.stream().map(user -> userMapper.toResource(user)).toList();
    }

    @Override
    public UserResourse save(UserResourse userResourse) {
        List<UserResourse> userResourseList = findByUsername(userResourse.getUsername());
        if (!userResourseList.isEmpty()){
            throw new UserDuplicateException("Attempt to insert duplicate of user.");
        }
        User user = new User(userResourse.getId(),
                userResourse.getUsername(),
                userResourse.getName(),
                userResourse.getSurname());
        userRepository.save(user);
        return userMapper.toResource(user);
    }
}
