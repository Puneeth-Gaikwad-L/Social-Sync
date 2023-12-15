package com.example.socialsync.model;

import com.example.socialsync.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
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

    @OneToMany
    List<Post> posts = new ArrayList<>();

    @OneToMany
    List<Comment> comments = new ArrayList<>();

    @OneToMany
    List<Friendship> friendships = new ArrayList<>();

    @OneToMany
    List<Follow> followers = new ArrayList<>();

    @OneToMany
    List<Follow> following = new ArrayList<>();

    public void setPassword(String plainPassword){
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        this.password = hashedPassword;
    }

    public boolean checkPassword(String plainPassword){
        return BCrypt.checkpw(plainPassword, this.password);
    }
}
