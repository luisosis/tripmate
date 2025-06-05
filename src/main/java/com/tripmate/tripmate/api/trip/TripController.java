package com.tripmate.tripmate.api.trip;

import com.tripmate.tripmate.application.trip.TripUseCase;
import com.tripmate.tripmate.domain.trip.Trip;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
@Slf4j
public class TripController {

    private final TripUseCase tripUseCase;

    @GetMapping
    public Observable<Trip> getTrips(){
        return tripUseCase.getTrips();
    }


}
