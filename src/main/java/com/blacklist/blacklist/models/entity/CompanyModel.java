package com.blacklist.blacklist.models.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "company")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany(mappedBy = "companyModel", cascade = CascadeType.ALL)
    List<BlockedUnitsModel> blockedUnitsModels;
}
