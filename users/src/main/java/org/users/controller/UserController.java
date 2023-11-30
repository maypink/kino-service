package org.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.users.exception.user.ResponseUserErrorException;
import org.users.exception.user.customException.UserNotFoundException;
import org.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.users.utils.UserResourse;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Operation(
            summary = "Get users from database by username."
    )
    @GetMapping("username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable @Parameter(description = "username") String username) {

        List<UserResourse> userResourseList = userService.findByUsername(username);
        if (userResourseList.isEmpty()){
            throw new UserNotFoundException("User with specified username was not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userResourseList);
        }
    }

    @Operation(
            summary = "Get all users from database."
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UserResourse> userResources = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResources);
    }

    @Operation(
            summary = "Add user."
    )
    @PostMapping("/new")
    public ResponseEntity<?> add(@RequestParam @Parameter(description = "username") String username,
                                 @RequestParam @Parameter(description = "name") String name,
                                 @RequestParam @Parameter(description = "surname") String surname) throws ResponseUserErrorException {

        UserResourse userResourse = new UserResourse(UUID.randomUUID(), username, name, surname);
        userService.save(userResourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResourse);
    }
}
