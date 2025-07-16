package com.tripmate.tripmate.api.trip;

import com.tripmate.tripmate.application.trip.TripUseCase;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import com.tripmate.tripmate.domain.trip.TripRecord;
import com.tripmate.tripmate.domain.trip.TripResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
@Slf4j
public class TripController {

    private final TripUseCase tripUseCase;

    @GetMapping
    public Observable<TripResponse> getTrips() {
        return tripUseCase.getTrips();
    }

    @GetMapping("/{id}/details")
    public Maybe<TripResponse> getTripDetails(@PathVariable Long id) {
        return tripUseCase.getTripById(id);
    }

    @GetMapping("/trip")
    public Maybe<TripAuxiliar> getTripByParams(@RequestParam String title) {
        return tripUseCase.getTripByParams(title);
    }

    @PostMapping("/save")
    public Completable save(@Valid @RequestBody TripRecord trip) {
        return tripUseCase.save(trip);
    }

    @GetMapping("/{id}/taxes")
    public Single<ResponseEntity<String>> calculateTaxes(@PathVariable Long id) {
        return tripUseCase.calculateTaxes(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Single.just(ResponseEntity.notFound().build()));
    }
}
