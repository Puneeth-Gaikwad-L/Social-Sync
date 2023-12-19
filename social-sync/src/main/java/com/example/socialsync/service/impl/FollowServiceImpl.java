package com.example.socialsync.service.impl;

import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.Follow;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.FollowRepository;
import com.example.socialsync.repositories.UserRepository;
import com.example.socialsync.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    @Override
    public String followUser(String followedUserName, String followerUserName){
        User followedUser = userRepository.findByUserName(followedUserName);
        User followerUser = userRepository.findByUserName(followerUserName);
        if (followedUser == null || followerUser == null) {
            throw new UserNotFoundException("user doesn't exist");
        }

        Follow follow = new Follow();

        follow.setFollowedUser(followedUser);
        follow.setFollowerUser(followerUser);

       Follow savedFollow = followRepository.save(follow);
       User user1 = savedFollow.getFollowedUser();
       User user2 = savedFollow.getFollowerUser();

       List<Follow> user1followers = user1.getFollowers();
       user1followers.add(savedFollow);

       List<Follow> user2following = user2.getFollowing();
       user2following.add(savedFollow);

       return savedFollow.getFollowerUser().getUserName() + " started following " +savedFollow.getFollowedUser().getUserName();
    }
}
