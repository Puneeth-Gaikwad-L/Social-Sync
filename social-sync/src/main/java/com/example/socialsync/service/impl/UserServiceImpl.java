package com.example.socialsync.service.impl;

import com.example.socialsync.dto.request.UserRequestDto;
import com.example.socialsync.dto.response.UserResponseDto;
import com.example.socialsync.exceptions.UserAlreadyExists;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.UserRepository;
import com.example.socialsync.service.UserService;
import com.example.socialsync.transformers.UserTransformer;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto){
        User userCheck = userRepository.findByEmailId(userRequestDto.getEmailId());
        if (userCheck != null) {
            throw new UserAlreadyExists("user Already registered");
        }
        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);
        user.setPassword(userRequestDto.getPassword());

        User savedUser = userRepository.save(user);

        return UserTransformer.UserToUserResponseDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDto updateBioandProfilePic(String userEmail, String newBio, String newProfilePicUrl){
        User user = userRepository.findByEmailId(userEmail);

        if (user == null){
            throw new UserNotFoundException("user not found");
        }

        if (!newBio.isEmpty()) {
            user.setBio(newBio);
        }
        if (!newProfilePicUrl.isEmpty()) {
            user.setProfilePicture(newProfilePicUrl);
        }

        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponseDto(savedUser);
    }

}
