package com.example.socialsync.service;

import com.example.socialsync.dto.request.CommentRequestDto;
import com.example.socialsync.dto.response.CommentResponseDto;

public interface CommentService {

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto);
}
