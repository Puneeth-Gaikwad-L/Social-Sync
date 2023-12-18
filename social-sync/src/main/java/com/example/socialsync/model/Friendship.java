package com.example.socialsync.model;

import com.example.socialsync.Enum.FriendshipStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "FriendShip")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String friendshipId;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false, referencedColumnName = "userName")
    User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false, referencedColumnName = "userName")
    User user2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    FriendshipStatus status;
}
