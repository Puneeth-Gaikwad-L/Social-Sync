package com.example.socialsync.transformers;

import com.example.socialsync.dto.request.CommentRequestDto;
import com.example.socialsync.dto.response.CommentResponseDto;
import com.example.socialsync.model.Comment;
import com.example.socialsync.model.Post;
import com.example.socialsync.model.User;

public class CommentTransformer {

    public static Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto){
        return Comment.builder()
                .content(commentRequestDto.getContent())
                .build();
    }

    public static CommentResponseDto CommentToCommentResponseDto(Comment comment){
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getPostUid())
                .build();
    }
}
