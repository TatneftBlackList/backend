package com.blacklist.blacklist.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockedUnitsCreateUpdateDTO {
    String fio;
    String passportSeria;
    String passportNumber;
    String oldPassportSeria;
    String oldPassportNumber;
    Long companyId;
    String reason;
    Date dateAddToList;
}
