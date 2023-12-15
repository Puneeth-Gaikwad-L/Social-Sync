package com.example.socialsync.transformers;

import com.example.socialsync.dto.request.PostRequestDto;
import com.example.socialsync.dto.response.PostResponseDto;
import com.example.socialsync.model.Post;

public class PostTransformer {

    public static Post postRequestToPost(PostRequestDto postRequestDto){
        return Post.builder()
                .content(postRequestDto.getContent())
                .mediaUrl(postRequestDto.getMediaUrl())
                .privacySetting(postRequestDto.getPrivacySetting())
                .build();
    }

    public static PostResponseDto postToPostResponceDto(Post post){
        return PostResponseDto.builder()
                .content(post.getContent())
                .mediaUrl(post.getMediaUrl())
                .postId(post.getPostId())
                .build();
    }
}
