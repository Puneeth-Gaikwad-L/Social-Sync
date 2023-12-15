package com.example.socialsync.dto.request;

import com.example.socialsync.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {
    String userName;

    String emailId;

    String password;

    String bio;

    String profilePicture;

    Gender gender;
}
