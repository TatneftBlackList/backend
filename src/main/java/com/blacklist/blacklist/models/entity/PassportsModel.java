package com.blacklist.blacklist.models.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "passports")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassportsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "passport_seria", unique = true, nullable = false)
    String passportSeria;

    @Column(name = "passport_number", unique = true, nullable = false)
    String passportNumber;

    @Column(name = "old_passport_seria", unique = true, nullable = true)
    String oldPassportSeria;

    @Column(name = "old_passport_number", unique = true, nullable = true)
    String oldPassportNumber;

    @OneToOne(mappedBy = "passportsModel")
    BlockedUnitsModel blockedUnitsModel;

}
