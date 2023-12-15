package com.example.socialsync.repositories;

import com.example.socialsync.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
    public Post findByPostId(String postId);
}
