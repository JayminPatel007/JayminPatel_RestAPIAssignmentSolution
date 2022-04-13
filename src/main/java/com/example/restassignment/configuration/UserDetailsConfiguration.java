package com.example.restassignment.configuration;

import com.example.restassignment.repository.UserRepository;
import com.example.restassignment.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserDetailsConfiguration {

    private UserRepository userRepository;

    public UserDetailsConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean(name = "userDetails")
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
