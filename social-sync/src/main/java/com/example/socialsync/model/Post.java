package com.example.socialsync.model;

import com.example.socialsync.Enum.PrivacySetting;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String postId;

    @Column(nullable = false)
    String content;

    @Column(name = "media_url")
    String mediaUrl;

    int likeCount;

    @CreationTimestamp
    LocalDateTime creationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PrivacySetting privacySetting;

    @ManyToOne
    @JoinColumn(name="user_name", nullable = false, referencedColumnName = "userName")
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> likedByUsers = new ArrayList<>();
}
