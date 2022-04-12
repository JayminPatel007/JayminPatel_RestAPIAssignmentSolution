package com.example.restassignment.configuration;

import com.example.restassignment.entity.Role;
import com.example.restassignment.entity.User;
import com.example.restassignment.repository.RoleRepository;
import com.example.restassignment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class PreLoadDataConfiguration {

    private PasswordEncoder passwordEncoder;

    public PreLoadDataConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner preload(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);

            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("Admin@123"));
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            adminUser.setRoles(adminRoles);
            userRepository.save(adminUser);
        };
    }
}
