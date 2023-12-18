package com.example.socialsync.service.impl;

import com.example.socialsync.Enum.ActivityType;
import com.example.socialsync.Enum.PrivacySetting;
import com.example.socialsync.dto.request.PostRequestDto;
import com.example.socialsync.dto.response.PostResponseDto;
import com.example.socialsync.exceptions.InvalidPostId;
import com.example.socialsync.exceptions.PrivatePost;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.Activity;
import com.example.socialsync.model.Post;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.ActivityRepository;
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

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto){

        User user = userRepository.findByEmailId(postRequestDto.getEmailId());

        if (user==null){
            throw new UserNotFoundException("enter a valid email");
        }

        Post newPost = PostTransformer.postRequestToPost(postRequestDto);
        newPost.setPostUid(String.valueOf(UUID.randomUUID()));
        newPost.setUser(user);
        Post savedPost = postRepository.save(newPost);

        Activity newActivity = new Activity();
        newActivity.setType(ActivityType.POST_CREATED);
        newActivity.setUser(user);
        newActivity.setPost(savedPost);

        activityRepository.save(newActivity);

        return PostTransformer.postToPostResponceDto(savedPost);
    }

    @Override
    public PostResponseDto rePost(String userEmail, String postId, PrivacySetting privacySetting){
        User user = userRepository.findByEmailId(userEmail);
        if (user == null) {
            throw new UserNotFoundException("user doesn't exist");
        }
        Post originalPost = postRepository.findByPostUid(postId);
        if (originalPost == null) {
            throw new InvalidPostId("invalid post Id");
        }

        if (originalPost.getPrivacySetting() == PrivacySetting.PUBLIC){
            Post repostedPost = new Post();
            repostedPost.setPostUid(String.valueOf(UUID.randomUUID()));
            repostedPost.setUser(user);
            repostedPost.setContent(originalPost.getContent());
            repostedPost.setMediaUrl(originalPost.getMediaUrl());
            repostedPost.setPrivacySetting(privacySetting);

            Post savedPost = postRepository.save(repostedPost);

            Activity newActivity = new Activity();
            newActivity.setType(ActivityType.POST_CREATED);
            newActivity.setUser(user);
            newActivity.setPost(savedPost);

            activityRepository.save(newActivity);

            return PostTransformer.postToPostResponceDto(savedPost);
        }else {
            throw new PrivatePost("private post cannot be reposted");
        }
    }

}
