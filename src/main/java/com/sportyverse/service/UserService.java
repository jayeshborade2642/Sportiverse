package com.sportyverse.service;

import com.sportyverse.dto.UserDto;
import com.sportyverse.dto.UserLoginDto;
import com.sportyverse.response.UserLoginResponse;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto updatedUser);

    void deleteUser(Long userId);

    UserLoginResponse loginUser(UserLoginDto UserLoginDto);
}
