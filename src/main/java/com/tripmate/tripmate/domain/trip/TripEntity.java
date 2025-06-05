package com.tripmate.tripmate.domain.trip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "trip")
@Getter
@Setter
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "destination_country")
    private String destinationCountry;

    @Column(name = "destination_city")
    private String destinationCity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createdAt;
}
