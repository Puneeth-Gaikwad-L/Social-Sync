package com.example.socialsync.service;

import com.example.socialsync.Enum.PrivacySetting;
import com.example.socialsync.dto.request.PostRequestDto;
import com.example.socialsync.dto.response.PostResponseDto;

public interface PostService {

    public PostResponseDto createPost(PostRequestDto postRequestDto);

    public PostResponseDto rePost(String userEmail, String postId, PrivacySetting privacySetting);
}
