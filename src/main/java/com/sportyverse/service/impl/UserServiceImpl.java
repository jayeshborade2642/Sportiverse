package com.sportyverse.service.impl;

import com.sportyverse.dto.UserDto;
import com.sportyverse.dto.UserLoginDto;
import com.sportyverse.entity.User;
import com.sportyverse.exception.ResourceNotFoundException;
import com.sportyverse.mapper.UserMapper;
import com.sportyverse.repository.UserRepository;
import com.sportyverse.response.UserLoginResponse;
import com.sportyverse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder userPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = userMapper.mapToUser(userDto);
        User savedUser =  userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User does not exists with given Id: " + userId));
        return userMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exists with the given Id: " + userId)
        );

        if (updatedUser.getFirstName() != null) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            user.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getPhone() != null) {
            user.setPhone(updatedUser.getPhone());
        }
        if (updatedUser.getAddress() != null) {
            user.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getGender() != null) {
            user.setGender(updatedUser.getGender());
        }

        User updatedUserObj =  userRepository.save(user);

        return userMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exists with the given Id: " + userId)
        );

        userRepository.delete(user);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginDto userLoginDto) {
        User user1 = userRepository.findByEmail(userLoginDto.getEmail());
        if (user1 != null) {
            String password = userLoginDto.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight = userPasswordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(userLoginDto.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new UserLoginResponse("Logged in Successfully!!", true);
                } else {
                    return new UserLoginResponse("Login Failed", false);
                }
            } else {
                return new UserLoginResponse("Password does not matched", false);
            }
        } else {
            return new UserLoginResponse("Email does not exists", false);
        }
    }
}
