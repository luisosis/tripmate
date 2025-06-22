package com.tripmate.tripmate.application.trip;

import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import jakarta.validation.Valid;

public interface TripUseCase {

    Observable<Trip> getTrips();

    Maybe<TripAuxiliar> getTripById(Long id);

    Completable save(Trip trip);
}
