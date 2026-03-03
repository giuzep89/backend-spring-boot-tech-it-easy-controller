package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.AuthenticationRequest;
import com.novi.techiteasycontroller.dtos.AuthenticationResponse;
import com.novi.techiteasycontroller.services.CustomUserDetailsService;
import com.novi.techiteasycontroller.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;


    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Incorrect username or password");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

