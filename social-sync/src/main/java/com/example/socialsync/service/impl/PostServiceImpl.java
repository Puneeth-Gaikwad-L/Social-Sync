package com.example.socialsync.service.impl;

import com.example.socialsync.dto.request.PostRequestDto;
import com.example.socialsync.dto.response.PostResponseDto;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.Post;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.PostRepository;
import com.example.socialsync.repositories.UserRepository;
import com.example.socialsync.service.PostService;
import com.example.socialsync.transformers.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto){

        User user = userRepository.findByEmailId(postRequestDto.getEmailId());

        if (user==null){
            throw new UserNotFoundException("enter a valid email");
        }

        Post newPost = PostTransformer.postRequestToPost(postRequestDto);
        newPost.setPostId(String.valueOf(UUID.randomUUID()));
        newPost.setUser(user);
        Post savedPost = postRepository.save(newPost);

        return PostTransformer.postToPostResponceDto(savedPost);
    }
}
