package com.tripmate.tripmate.domain.trip;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository {

    Observable<Trip> getTrips();
}
