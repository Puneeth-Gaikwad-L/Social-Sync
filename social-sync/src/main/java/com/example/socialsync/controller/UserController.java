package com.example.socialsync.controller;


import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import com.example.socialsync.exceptions.UserAlreadyExists;
import com.example.socialsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
        try{
            UserResponseDto userResponseDto =userService.addUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }catch (UserAlreadyExists e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
