package com.example.socialsync.controller;

import com.example.socialsync.dto.request.CommentRequestDto;
import com.example.socialsync.dto.response.CommentResponseDto;
import com.example.socialsync.exceptions.InvalidPostId;
import com.example.socialsync.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity createComment(@RequestBody CommentRequestDto commentRequestDto){
        try{
            CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto);
            return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
        }catch (InvalidPostId e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
