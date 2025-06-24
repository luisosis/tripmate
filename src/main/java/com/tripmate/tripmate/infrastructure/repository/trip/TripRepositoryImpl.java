package com.tripmate.tripmate.infrastructure.repository.trip;

import com.tripmate.tripmate.domain.mapper.TripMapper;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TripRepositoryImpl implements TripRepository {

    private final TripJpaRepository tripJpaRepository;
    private final TripMapper tripMapper;

    @Override
    public Observable<Trip> getTrips() {
        return Observable.fromIterable(tripJpaRepository.findAll())
                .subscribeOn(Schedulers.io())
                //.flatMap(Observable::fromIterable)
                .map(tripMapper::toDto);
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

    @Override
    public Maybe<Trip> getTripByTittle(String tittle) {
        return Maybe.fromFuture(tripJpaRepository.findByTitle(tittle))
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

    @Override
    public Completable save(Trip trip) {
        return Single.fromCallable(() -> tripJpaRepository.save(tripMapper.toEntity(trip)))
                .doOnSuccess(entity -> log.info("Register entity success"))
                .ignoreElement();
    }
}
