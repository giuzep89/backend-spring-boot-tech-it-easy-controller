package com.novi.techiteasycontroller.controllers;

import com.novi.techiteasycontroller.dtos.UserDto;
import com.novi.techiteasycontroller.dtos.UserInputDto;
import com.novi.techiteasycontroller.models.Authority;
import com.novi.techiteasycontroller.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserInputDto inputDto) {
        UserDto newUser = userService.createUser(inputDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUser.username)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username,
                                                   @RequestBody Map<String, String> fields) {
        String authorityName = fields.get("authority");
        Authority newAuthority = new Authority(username, authorityName);

        userService.addAuthority(username, newAuthority);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
