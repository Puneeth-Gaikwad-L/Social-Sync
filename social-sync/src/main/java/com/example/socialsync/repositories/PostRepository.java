package com.example.socialsync.repositories;

import com.example.socialsync.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    public Post findByPostUid(String postId);

    @Query("SELECT p FROM Post p WHERE p.privacySetting = 'PUBLIC'")
    public List<Post> getPublicPosts();

}
