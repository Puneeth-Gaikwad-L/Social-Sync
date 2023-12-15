package com.example.socialsync.service;

import com.example.socialsync.dto.request.PostRequestDto;
import com.example.socialsync.dto.response.PostResponseDto;

public interface PostService {

    public PostResponseDto createPost(PostRequestDto postRequestDto);
}
