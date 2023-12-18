package com.example.socialsync.service.impl;

import com.example.socialsync.Enum.FriendshipStatus;
import com.example.socialsync.exceptions.FriendshipNotFound;
import com.example.socialsync.exceptions.UserNotFoundException;
import com.example.socialsync.model.Friendship;
import com.example.socialsync.model.User;
import com.example.socialsync.repositories.FriendshipRepository;
import com.example.socialsync.repositories.UserRepository;
import com.example.socialsync.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipRepository friendshipRepository;

    @Override
    public String sendFriendRequest(String user1Email, String user2Email){
        User user1 = userRepository.findByEmailId(user1Email);

        if (user1 == null) {
            throw new UserNotFoundException("user with "+user1Email+" not found");
        }

        User user2 = userRepository.findByEmailId(user2Email);

        if (user2 == null) {
            throw new UserNotFoundException("user with "+user2Email+" not found");
        }

        Friendship friendship = new Friendship();
        friendship.setFriendshipId(String.valueOf(UUID.randomUUID()));
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        friendship.setStatus(FriendshipStatus.PENDING);

        friendshipRepository.save(friendship);

        return "friend request sent";
    }

    @Override
    public void acceptFriendRequest(String friendshipId){
        Friendship request = friendshipRepository.findByFriendshipId(friendshipId);
        if (request == null) {
            throw new FriendshipNotFound("Invalid id");
        }
//        User user1 = request.getUser1();
//        User user2 = request.getUser2();
//
//        List<Friendship> user1friends = user1.getFriendships();
//
//        user1friends.add(request);
//
//        userRepository.save(user1);
//
//        List<Friendship> user2friends = user2.getFriendships();
//
//        user2friends.add(request);
//
//        userRepository.save(user2);


        request.setStatus(FriendshipStatus.ACCEPTED);

        friendshipRepository.save(request);
    }
}
