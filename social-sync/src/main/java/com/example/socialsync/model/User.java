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
@Table(name = "users")
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
    List<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Feed feed;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();


    List<String> friendships = new ArrayList<>();


    List<String> followers = new ArrayList<>();


    List<String> following = new ArrayList<>();

    public void setPassword(String plainPassword){
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        this.password = hashedPassword;
    }

    public boolean checkPassword(String plainPassword){
        return BCrypt.checkpw(plainPassword, this.password);
    }
}
