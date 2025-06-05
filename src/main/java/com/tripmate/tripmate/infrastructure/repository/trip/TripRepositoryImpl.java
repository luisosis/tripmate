package com.tripmate.tripmate.infrastructure.repository.trip;

import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TripRepositoryImpl implements TripRepository {

    private final TripJpaRepository tripJpaRepository;

    @Override
    public Observable<Trip> getTrips() {
        return Observable.fromIterable(tripJpaRepository.findAll())
                //.subscribeOn(Schedulers.io())
                //.flatMap(Observable::fromIterable)
                .map(tripEntity -> Trip.builder()
                        .title(tripEntity.getTitle())
                        .description(tripEntity.getDescription())
                        .destinationCity(tripEntity.getDestinationCity())
                        .destinationCountry(tripEntity.getDestinationCountry())
                        .startDate(tripEntity.getStartDate())
                        .endDate(tripEntity.getEndDate())
                        .build());
    }

    @Override
    public Maybe<Trip> getTripById(Long id) {
        return Maybe.fromOptional(tripJpaRepository.findById(id))
                //.subscribeOn(Schedulers.io())
                .map(tripEntity -> Trip.builder()
                        .title(tripEntity.getTitle())
                        .description(tripEntity.getDescription())
                        .destinationCity(tripEntity.getDestinationCity())
                        .destinationCountry(tripEntity.getDestinationCountry())
                        .startDate(tripEntity.getStartDate())
                        .endDate(tripEntity.getEndDate())
                        .build());
    }
}
