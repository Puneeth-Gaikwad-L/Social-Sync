package com.example.socialsync.model;

import com.example.socialsync.Enum.PrivacySetting;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String content;

    @Column(name = "media_url")
    String mediaUrl;

    @CreationTimestamp
    LocalDateTime creationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PrivacySetting privacySetting;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    Set<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> likedByUsers;
}
