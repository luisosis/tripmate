package com.tripmate.tripmate.infrastructure.repository.trip;

import com.tripmate.tripmate.domain.TripType;
import com.tripmate.tripmate.domain.mapper.TripMapper;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripRecord;
import com.tripmate.tripmate.domain.trip.TripRepository;
import com.tripmate.tripmate.domain.trip.TripResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TripRepositoryImpl implements TripRepository {

    private final TripJpaRepository tripJpaRepository;
    private final TripMapper tripMapper;

    @Override
    public Observable<TripResponse> getTrips() {
        return Observable.fromIterable(tripJpaRepository.findAll())
                .subscribeOn(Schedulers.io())
                //.flatMap(Observable::fromIterable)
                .map(tripMapper::toDto);
    }

    @Override
    public Maybe<TripResponse> getTripById(Long id) {
        return Maybe.fromOptional(tripJpaRepository.findById(id))
                .subscribeOn(Schedulers.io())
                .map(tripEntity -> new TripResponse(
                        tripEntity.getTitle(),
                        tripEntity.getTripType(),
                        tripEntity.getDescription(),
                        tripEntity.getDestinationCountry(),
                        tripEntity.getDestinationCity(),
                        tripEntity.getStartDate(),
                        tripEntity.getEndDate()));
    }

    @Override
    public Maybe<Trip> getTripByTittle(String tittle) {
        return Maybe.fromFuture(tripJpaRepository.findByTitle(tittle))
                .subscribeOn(Schedulers.io())
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
    public Completable save(TripRecord trip) {
        return Single.fromCallable(() -> tripJpaRepository.save(tripMapper.toEntity(trip)))
                .doOnSuccess(entity -> log.info("Register entity success"))
                .ignoreElement();
    }
}
