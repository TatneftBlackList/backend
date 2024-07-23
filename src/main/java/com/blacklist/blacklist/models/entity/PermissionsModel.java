package com.blacklist.blacklist.models.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "created")
    boolean created;

    @Column(name = "read")
    boolean read;

    @Column(name = "delete")
    boolean delete;

    @Column(name = "update")
    boolean update;


    @ManyToMany(mappedBy = "permissions")
    Set<UsersModel> usersModels = new HashSet<>();

}
