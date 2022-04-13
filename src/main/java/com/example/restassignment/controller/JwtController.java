package com.example.restassignment.controller;

import com.example.restassignment.dto.request.AuthRequest;
import com.example.restassignment.dto.response.AuthResponse;
import com.example.restassignment.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    private AuthService authService;

    public JwtController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public AuthResponse createJwtToken(@RequestBody AuthRequest authRequest) throws Exception {
        return authService.createAccessToken(authRequest);
    }
}
