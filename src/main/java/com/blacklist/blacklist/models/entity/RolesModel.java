package com.blacklist.blacklist.models.entity;

import com.blacklist.blacklist.models.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    ERole name;

    @OneToMany(mappedBy = "rolesModel")
    Set<AuthModel> users;

}

