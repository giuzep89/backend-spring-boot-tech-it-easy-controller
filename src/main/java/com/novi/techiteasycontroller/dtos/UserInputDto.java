package com.novi.techiteasycontroller.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserInputDto {

    @NotBlank(message = "Please fill in username")
    public String username;
    @NotBlank(message = "Please choose a password")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    public String password;
    @NotBlank(message = "Please type your email address")
    public String email;
}