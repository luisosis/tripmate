package com.tripmate.tripmate.domain.itinerary;

import com.tripmate.tripmate.domain.trip.TripEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itinerary")
@Getter
@Setter
public class ItineraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_number", nullable = false)
    private int dayNumber;

    private String activity;

    private String time;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private TripEntity trip;
}
