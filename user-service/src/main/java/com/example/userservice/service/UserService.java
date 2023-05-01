package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    Iterable<UserEntity> getAllUsers();
}
