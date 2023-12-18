package com.example.socialsync.repositories;

import com.example.socialsync.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

    public Friendship findByFriendshipId(String friendshipId);
}
