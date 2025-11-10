package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.UserDto;
import com.novi.techiteasycontroller.mappers.UserMapper;
import com.novi.techiteasycontroller.models.User;
import com.novi.techiteasycontroller.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET all users
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto dto = UserMapper.toDto(user);
            userDtos.add(dto);
        }

        return userDtos;
    }

    // GET by username
    public UserDto getUser(String username) {
        User userToFind = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username  + " not found"));

        return UserMapper.toDto(userToFind);
    }








}
