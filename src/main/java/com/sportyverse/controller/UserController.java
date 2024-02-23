package com.sportyverse.controller;

import com.sportyverse.dto.UserDto;
import com.sportyverse.dto.UserLoginDto;
import com.sportyverse.response.UserLoginResponse;
import com.sportyverse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    //Add User RestAPI
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Get User RestAPI
    @GetMapping("{uid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("uid") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    //Get All Users RestAPI
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Update User RestAPI
    @PutMapping("{uid}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("uid") Long userId,
                                              @RequestBody UserDto updatedUser){
        UserDto userDto = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    //Delete User RestAPI
    @DeleteMapping("{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable("uid") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully..!!");
    }

    //Login User RestAPI
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDTO)
    {
        UserLoginResponse userloginResponse = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(userloginResponse);
    }
}
