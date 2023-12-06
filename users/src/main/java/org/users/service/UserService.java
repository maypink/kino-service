package org.users.service;

import org.springframework.stereotype.Service;
import org.users.utils.UserResource;

import java.util.List;

@Service
public interface UserService {

    List<UserResource> getAllUsers();

    List<UserResource> findByUsername(String username);

    UserResource save(UserResource userResource);
}
