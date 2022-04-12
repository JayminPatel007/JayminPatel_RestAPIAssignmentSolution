package com.example.restassignment.service;

import com.example.restassignment.dto.request.JwtRequest;
import com.example.restassignment.dto.response.JwtResponse;
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
public class JwtService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("userDetails")
    private UserDetailsService userDetailsService;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        authenticate(username, password);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String accessToken = jwtUtils.generateToken(userDetails);
        User user = userRepository.findById(username).get();
        return new JwtResponse(user, accessToken);
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
