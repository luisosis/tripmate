package com.tripmate.tripmate.domain.trip;

import com.tripmate.tripmate.domain.TripType;
import com.tripmate.tripmate.domain.expense.ExpenseEntity;
import com.tripmate.tripmate.domain.itinerary.ItineraryEntity;
import com.tripmate.tripmate.domain.place.PlaceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<ItineraryEntity> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ExpenseEntity> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PlaceEntity> places = new ArrayList<>();
}
