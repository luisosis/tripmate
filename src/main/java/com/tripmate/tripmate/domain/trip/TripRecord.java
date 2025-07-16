package com.tripmate.tripmate.domain.trip;

import com.tripmate.tripmate.domain.TripType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record TripRecord(
        @NotNull
        @Size(min = 2, max = 30)
        String title,
        @NotNull TripType tripType,
        String description,
        String destinationCountry,
        String destinationCity,
        LocalDate startDate,
        LocalDate endDate
) {}




