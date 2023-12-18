package com.example.socialsync.service;

import com.example.socialsync.model.Friendship;
import com.example.socialsync.model.User;

import java.util.List;

public interface FriendshipService {

    public String sendFriendRequest(String user1Email, String user2Email);

    public void acceptFriendRequest(String friendshipId);
}
