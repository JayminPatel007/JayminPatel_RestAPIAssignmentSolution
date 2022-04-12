package com.example.restassignment.service;

import com.example.restassignment.entity.Role;
import com.example.restassignment.entity.User;
import com.example.restassignment.repository.RoleRepository;
import com.example.restassignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerNewUser(User user) {
        return userRepository.save(user);
    }

    public void initRolesAndUsers() {
    }
}
