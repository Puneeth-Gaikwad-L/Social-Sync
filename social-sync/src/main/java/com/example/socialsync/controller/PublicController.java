package com.example.socialsync.controller;

import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import com.example.socialsync.exceptions.UserAlreadyExists;
import com.example.socialsync.exceptions.UserNameExists;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
        try{
            UserResponseDto userResponseDto =userService.addUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }catch (UserAlreadyExists e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (UserNameExists u){
//            username shd be unique
            return new ResponseEntity<>(u.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String userEmail, String password){
        try {
            UserResponseDto userResponseDto = userService.login(userEmail, password);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
