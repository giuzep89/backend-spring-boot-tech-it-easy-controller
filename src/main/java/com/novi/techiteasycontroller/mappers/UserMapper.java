package com.novi.techiteasycontroller.mappers;

import com.novi.techiteasycontroller.dtos.UserDto;
import com.novi.techiteasycontroller.dtos.UserInputDto;
import com.novi.techiteasycontroller.models.User;

public class UserMapper {

    public static User toEntity(UserInputDto dto) {
        User entity = new User();

        entity.setUsername(dto.username);
        entity.setEmail(dto.email);
        entity.setPassword(dto.password);

        return entity;
    }

    public static UserDto toDto(User entity) {
        UserDto dto = new UserDto();

        dto.username = entity.getUsername();
        dto.email = entity.getEmail();
        dto.password = entity.getPassword();

        return dto;
    }


}
