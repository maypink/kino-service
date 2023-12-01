package org.users.service;

import org.springframework.stereotype.Service;
import org.users.utils.UserResourse;

import java.util.List;

@Service
public interface UserService {

    List<UserResourse> getAllUsers();

    List<UserResourse> findByUsername(String username);

    UserResourse save(UserResourse userResourse);
}
