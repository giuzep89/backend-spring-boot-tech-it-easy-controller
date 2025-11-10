package com.novi.techiteasycontroller.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.novi.techiteasycontroller.models.Authority;

import java.util.Set;

public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String email;

    @JsonSerialize
    public Set<Authority> authorities;
}
