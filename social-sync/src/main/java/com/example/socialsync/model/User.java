package com.example.socialsync.model;

import com.example.socialsync.Enum.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String userName;

    @Column(unique = true, nullable = false)
    String emailId;

    @Column(nullable = false)
    String password;

    String bio;

    @Column(name = "profile_picture_url")
    String profilePicture;

    @CreationTimestamp
    Date joinedOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Friendship> friendships;

    @OneToMany(mappedBy = "followerUser", cascade = CascadeType.ALL)
    List<Follow> followers;

    @OneToMany(mappedBy = "followedUser", cascade = CascadeType.ALL)
    List<Follow> following;
}
