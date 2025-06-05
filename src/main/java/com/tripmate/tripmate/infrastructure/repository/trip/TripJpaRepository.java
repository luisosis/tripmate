package com.tripmate.tripmate.infrastructure.repository.trip;

import com.tripmate.tripmate.domain.trip.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripJpaRepository extends JpaRepository<TripEntity, Long> {

//    @Async
//    CompletableFuture<List<TripEntity>> getAllTrips();
}
