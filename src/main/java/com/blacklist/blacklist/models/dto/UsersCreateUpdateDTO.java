package com.blacklist.blacklist.models.dto;

import com.blacklist.blacklist.models.enums.EPermissions;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersCreateUpdateDTO {

    String jobNumber;
    String firstName;
    String lastName;
    Set<EPermissions> permissions;
}
