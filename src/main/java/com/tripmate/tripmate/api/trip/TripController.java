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
import io.reactivex.rxjava3.internal.operators.single.SingleFromCallable;
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
        log.info("GET /api/v1/trip - get List trips");
        return tripUseCase.getTrips();
    }

    @GetMapping("/{id}/details")
    public Maybe<TripResponse> getTripDetails(@PathVariable Long id) {
        log.info("GET /api/v1/trip/{}/details - Obtain detail trip", id);
        return tripUseCase.getTripById(id)
                .doOnError(thr ->
                        log.error("Error obtaint detail with id {}: {}", id, thr.getMessage()));
    }

    @GetMapping("/trip")
    public Maybe<TripAuxiliar> getTripByParams(@RequestParam String title) {
        log.debug("GET /api/v1/trip/trip?title={} - Buscando viaje por título", title);
        return tripUseCase.getTripByParams(title)
                .doOnComplete(() -> log.info("Búsqueda completada para título: {}", title))
                .doOnError(e ->
                        log.error("Error buscando viaje por título '{}': {}", title, e.getMessage()));
    }

    @PostMapping("/save")
    public Completable save(@Valid @RequestBody TripRecord trip) {
        log.info("POST /api/v1/trip/save - Guardando nuevo viaje: {}", trip.title());
        return tripUseCase.save(trip);
    }

    @GetMapping("/{id}/taxes")
    public Single<ResponseEntity<String>> calculateTaxes(@PathVariable Long id) {
        log.info("GET /api/v1/trip/{}/taxes - Calculando impuestos", id);
        return tripUseCase.calculateTaxes(id)
                .doOnError(e ->
                        log.error("Error calculando impuestos para tripId {}: {}", id, e.getMessage()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Single.fromCallable(() -> {
                    log.warn("No se encontraron impuestos para el trip con id {}", id);
                    return ResponseEntity.notFound().build();
                }));
                //.switchIfEmpty(Single.just(ResponseEntity.notFound().build()));
    }
}
