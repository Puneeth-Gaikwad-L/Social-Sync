package com.example.socialsync.service.impl;

import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.UserRepository;
import com.example.socialsync.service.UserService;
import com.example.socialsync.transformers.UserTransformer;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto){
        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);
        user.setPassword(userRequestDto.getPassword());

        User savedUser = userRepository.save(user);

        return UserTransformer.UserToUserResponseDto(savedUser);
    }

}
