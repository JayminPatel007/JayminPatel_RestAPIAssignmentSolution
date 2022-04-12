package com.example.restassignment.dto.response;

import com.example.restassignment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private User user;
    private String accessToken;
}
