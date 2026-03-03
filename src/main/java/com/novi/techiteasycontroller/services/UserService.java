package com.novi.techiteasycontroller.services;

import com.novi.techiteasycontroller.dtos.UserDto;
import com.novi.techiteasycontroller.dtos.UserInputDto;
import com.novi.techiteasycontroller.mappers.UserMapper;
import com.novi.techiteasycontroller.models.Authority;
import com.novi.techiteasycontroller.models.User;
import com.novi.techiteasycontroller.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto dto = UserMapper.toDto(user);
            userDtos.add(dto);
        }

        return userDtos;
    }

    public UserDto getUser(String username) {
        User userToFind = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username  + " not found"));

        return UserMapper.toDto(userToFind);
    }

    public UserDto createUser(UserInputDto userInputDto) {
        User user  = UserMapper.toEntity(userInputDto);

        String encodedPassword = passwordEncoder.encode(userInputDto.password);

        user.setPassword(encodedPassword);

        Authority authority = new Authority(user.getUsername(),  "ROLE_USER");

        user.addAuthority(authority);

        userRepository.save(user);

        return UserMapper.toDto(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserInputDto userInputDto) {
        User userToFind = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username  + " not found"));

        userToFind.setUsername(userInputDto.username);
        userToFind.setEmail(userInputDto.email);

        if (userInputDto.password != null && !userInputDto.password.isBlank() ) {
            userToFind.setPassword(passwordEncoder.encode(userInputDto.password));
        }

        userRepository.save(userToFind);
    }

    // this is mandatory as it gets called by Spring Security (UserDetails)
    public Set<Authority> getAuthorities(String username) {
        User userToFind = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username  + " not found"));

        return userToFind.getAuthorities();
    }

    public void addAuthority(String username, Authority authority) {
        User userToFind = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username  + " not found"));

        userToFind.addAuthority(authority);
        authority.setUser(userToFind);
        userRepository.save(userToFind);
    }

    public void removeAuthority(String username, Authority authority) {
        User userToFind = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username  + " not found"));

        userToFind.removeAuthority(authority);
        userRepository.save(userToFind);
    }










}
