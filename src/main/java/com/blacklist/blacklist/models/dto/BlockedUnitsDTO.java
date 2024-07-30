package com.blacklist.blacklist.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockedUnitsDTO {
    Long id;
    String fio;
    Long passportId;
    Long companyId;
    String reason;
    Date dateAddToList;
}
