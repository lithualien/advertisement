package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.models.User;
import com.github.lithualien.advertisement.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Set<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
