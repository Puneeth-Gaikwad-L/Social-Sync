package com.example.socialsync.dto.request;

import com.example.socialsync.Enum.PrivacySetting;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDto {

    String emailId;

    String content;

    String mediaUrl;

    PrivacySetting privacySetting;
}
