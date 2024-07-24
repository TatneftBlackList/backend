package com.blacklist.blacklist.models.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "black_units")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockedUnitsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fio")
    String fio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    PassportsModel passportsModel;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    CompanyModel companyModel;

    @Column(name = "reason")
    String reason;

    @Column(name = "date_add_to_list")
    Date dateAddToList;
}
