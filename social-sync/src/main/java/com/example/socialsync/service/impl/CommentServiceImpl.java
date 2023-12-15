package com.example.socialsync.service.impl;

import com.example.socialsync.Enum.ActivityType;
import com.example.socialsync.dto.request.CommentRequestDto;
import com.example.socialsync.dto.response.CommentResponseDto;
import com.example.socialsync.exceptions.InvalidPostId;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.Activity;
import com.example.socialsync.model.Comment;
import com.example.socialsync.model.Post;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.ActivityRepository;
import com.example.socialsync.repositories.CommentRepository;
import com.example.socialsync.repositories.PostRepository;
import com.example.socialsync.repositories.UserRepository;
import com.example.socialsync.service.CommentService;
import com.example.socialsync.transformers.CommentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto){
        Post post = postRepository.findByPostId(commentRequestDto.getPostId());
        if (post == null) {
            throw new InvalidPostId("Post not found");
        }
        User user = userRepository.findByEmailId(commentRequestDto.getUserEmail());
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        Comment comment = CommentTransformer.commentRequestDtoToComment(commentRequestDto);

        comment.setCommentId(String.valueOf(UUID.randomUUID()));
        comment.setUser(user);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        Activity activity = new Activity();
        activity.setType(ActivityType.COMMENT_CREATED);
        activity.setUser(user);
        activity.setPost(post);
        activity.setComment(comment);

        activityRepository.save(activity);
        return CommentTransformer.CommentToCommentResponseDto(savedComment);
    }
}
