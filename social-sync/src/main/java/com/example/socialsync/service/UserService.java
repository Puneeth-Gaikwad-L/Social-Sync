package com.example.socialsync.service;

import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;


public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);
}
