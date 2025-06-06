package com.tripmate.tripmate.domain.trip;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripAuxiliar {
    private String title;
    private String description;
    private String destinationCountry;
    private String destinationCity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String exchange;
}
