package com.example.socialsync.controller;

import com.example.socialsync.Enum.PrivacySetting;
import com.example.socialsync.dto.request.PostRequestDto;
import com.example.socialsync.dto.response.PostResponseDto;
import com.example.socialsync.exceptions.InvalidPostId;
import com.example.socialsync.exceptions.PrivatePost;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.service.PostService;
import com.example.socialsync.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity createPost(@RequestBody PostRequestDto postRequestDto){
        try {
            PostResponseDto postResponseDto = postService.createPost(postRequestDto);
            return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/repost")
    public ResponseEntity rePost(@RequestParam String userEmail, @RequestParam String postId, @RequestParam PrivacySetting privacySetting){
        try{
            PostResponseDto postResponseDto = postService.rePost(userEmail, postId, privacySetting);
            return new ResponseEntity<>(postResponseDto, HttpStatus.CREATED);
        }catch (UserNotFoundException | InvalidPostId | PrivatePost u){
            return new ResponseEntity<>(u.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
