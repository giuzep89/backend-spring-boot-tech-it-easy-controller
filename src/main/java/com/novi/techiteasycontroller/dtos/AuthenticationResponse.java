package com.novi.techiteasycontroller.dtos;

public class AuthenticationResponse {

    // Use the field name 'jwt' for clarity
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}