package com.tripmate.tripmate.application.trip;

import com.tripmate.tripmate.domain.trip.Trip;
import io.reactivex.rxjava3.core.Observable;

public interface TripUseCase {

    Observable<Trip> getTrips();
}
