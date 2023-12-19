package com.example.socialsync.service;

import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;


public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    public UserResponseDto updateBioandProfilePic(String userEmail, String newBio, String newProfilePicUrl);

    public UserResponseDto login(String userEmail, String password);
}
