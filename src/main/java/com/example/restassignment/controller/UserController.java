package com.example.restassignment.controller;

import com.example.restassignment.entity.User;
import com.example.restassignment.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return this.userService.registerNewUser(user);
    }

    @GetMapping("/forAdmin")
    public String forAdmin() {
        return "This URL should only be accessible by Admin";
    }

    @GetMapping("/forUser")
    public String forUser() {
        return "This URL should be accessible by User and Admin";
    }
}
