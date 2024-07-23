package com.blacklist.blacklist.models.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "auth")
public class AuthModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Column(name = "created_at")
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RolesModel rolesModel;

}
