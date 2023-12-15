package com.example.socialsync.transformers;

import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import com.example.socialsync.model.User;

public class UserTransformer {

    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .userName(userRequestDto.getUserName())
                .emailId(userRequestDto.getEmailId())
                .password(userRequestDto.getPassword())
                .bio(userRequestDto.getBio())
                .profilePicture(userRequestDto.getProfilePicture())
                .gender(userRequestDto.getGender())
                .build();
    }

    public static UserResponseDto UserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .userName(user.getUserName())
                .emailId(user.getEmailId())
                .bio(user.getBio())
                .build();
    }
}
