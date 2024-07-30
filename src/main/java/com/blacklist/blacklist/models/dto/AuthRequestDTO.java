package com.blacklist.blacklist.models.dto;

import lombok.*;
        import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequestDTO {
    String login;
    String password;
    Long roleId;
}
