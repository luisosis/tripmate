package com.tripmate.tripmate.domain.trip;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    private String title;
    private String description;
    private String destinationCountry;
    private String destinationCity;
    private LocalDate startDate;
    private LocalDate endDate;
}
