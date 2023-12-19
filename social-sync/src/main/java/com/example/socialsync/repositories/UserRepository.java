package com.example.socialsync.repositories;

import com.example.socialsync.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmailId(String emailId);

    public User findByUserName(String userName);
}
