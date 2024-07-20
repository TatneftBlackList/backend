package com.blacklist.blacklist.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "passports")
public class Passports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_seria", unique = true, nullable = false)
    private String passportSeria;

    @Column(name = "passport_number", unique = true, nullable = false)
    private String passportNumber;

    @Column(name = "old_passport_seria", unique = true, nullable = true)
    private String oldPassportSeria;

    @Column(name = "old_passport_seria", unique = true, nullable = true)
    private String oldPassportNumber;

    @OneToOne(mappedBy = "passports")
    private BlockedUnits blockedUnits;

}
