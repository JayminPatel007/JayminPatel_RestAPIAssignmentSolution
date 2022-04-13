package com.example.restassignment.service;

import com.example.restassignment.dto.request.AuthRequest;
import com.example.restassignment.dto.response.AuthResponse;
import com.example.restassignment.entity.User;
import com.example.restassignment.repository.UserRepository;
import com.example.restassignment.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("userDetails")
    private UserDetailsService userDetailsService;

    public AuthResponse createAccessToken(AuthRequest authRequest) throws Exception {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        authenticate(username, password);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String accessToken = jwtUtils.generateToken(userDetails);
        User user = userRepository.findById(username).get();
        return new AuthResponse(user, accessToken);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password ));
        } catch (DisabledException e) {
            throw new Exception("User is disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials");
        }
    }
}
