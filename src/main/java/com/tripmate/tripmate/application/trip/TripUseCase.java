package com.tripmate.tripmate.application.trip;

import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import com.tripmate.tripmate.domain.trip.TripRecord;
import com.tripmate.tripmate.domain.trip.TripResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.http.ResponseEntity;

public interface TripUseCase {

    Observable<TripResponse> getTrips();

    Maybe<TripResponse> getTripById(Long id);

    Maybe<TripAuxiliar> getTripByParams(String title);

    Completable save(TripRecord trip);

    Maybe<String> calculateTaxes(Long id);
}
