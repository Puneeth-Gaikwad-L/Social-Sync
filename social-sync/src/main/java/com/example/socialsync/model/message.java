package com.example.socialsync.model;

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
@Table(name = "message")
public class message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String content;

    @ManyToOne
    @JoinColumn(name = "sender_user_id", nullable = false)
    User senderUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id", nullable = false)
    User receiverUser;

    @Column(name = "sent_time", nullable = false)
    LocalDateTime sentTime;
}
