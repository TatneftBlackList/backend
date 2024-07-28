package com.blacklist.blacklist.models.entity;

import com.blacklist.blacklist.models.enums.EPermissions;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "permissions")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission", columnDefinition = "permission")
    private EPermissions ePermissions;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<UsersModel> usersModels = new HashSet<>();
}
