package com.sportyverse.mapper;

import com.sportyverse.dto.UserDto;
import com.sportyverse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getGender()
        );
    }

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                this.userPasswordEncoder.encode(userDto.getPassword()),
                userDto.getPhone(),
                userDto.getAddress(),
                userDto.getGender()
        );
    }
}
