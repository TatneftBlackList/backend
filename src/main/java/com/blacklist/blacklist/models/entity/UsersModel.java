package com.blacklist.blacklist.models.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersModel {

    @Id
    @Column(name = "job_number", unique = true)
    String jobNumber;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @ManyToMany
    @JoinTable(
        name = "user_permissions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "permissions_id")
    )
    Set<PermissionsModel> permissions = new HashSet<>();
}
