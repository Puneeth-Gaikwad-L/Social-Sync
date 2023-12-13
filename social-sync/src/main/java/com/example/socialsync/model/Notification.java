package com.example.socialsync.model;

import com.example.socialsync.Enum.NotificationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    NotificationType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "related_user_id")
    User relatedUser;

    @ManyToOne
    @JoinColumn(name = "related_post_id")
    Post relatedPost;

    @ManyToOne
    @JoinColumn(name = "related_comment_id")
    Comment relatedComment;

    @Column(name = "notification_time", nullable = false)
    LocalDateTime notificationTime;

}
