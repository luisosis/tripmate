package com.tripmate.tripmate.application.trip;

import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripRepository;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripUseCaseImpl implements TripUseCase{

    private final TripRepository tripRepository;

    @Override
    public Observable<Trip> getTrips() {
        return tripRepository.getTrips();
    }
}
