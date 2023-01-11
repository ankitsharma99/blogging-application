package com.backend.bloggapp.controllers;

import com.backend.bloggapp.exceptions.ApiException;
import com.backend.bloggapp.payloads.JwtAuthRequest;
import com.backend.bloggapp.payloads.JwtAuthResponse;
import com.backend.bloggapp.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword()); // function below
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String generatedToken = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(generatedToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e) {
            System.out.println("Invalid Details");
            throw new ApiException("Invalid Username or Password");
        }
    }
}
