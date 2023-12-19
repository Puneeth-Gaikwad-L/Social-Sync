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

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false, referencedColumnName = "userName")
    User user;

    String friendShipId;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false, referencedColumnName = "userName")
    User friend;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    FriendshipStatus status;
}
