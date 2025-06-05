package com.tripmate.tripmate.domain.place;

import com.tripmate.tripmate.domain.trip.TripEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "place")
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String location;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private TripEntity trip;
}
