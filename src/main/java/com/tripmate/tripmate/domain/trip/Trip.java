package com.tripmate.tripmate.domain.trip;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @NotNull
    //@NotEmpty
    //@NotBlank
    @Size(min = 2, max = 30)
    private String title;

    private String description;
    private String destinationCountry;
    private String destinationCity;
    private LocalDate startDate;
    private LocalDate endDate;
}
