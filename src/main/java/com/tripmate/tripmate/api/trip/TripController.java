package com.tripmate.tripmate.api.trip;

import com.tripmate.tripmate.application.trip.TripUseCase;
import com.tripmate.tripmate.domain.User;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

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

    @GetMapping("/{id}/details")
    public Maybe<TripAuxiliar> getTripDetails(@PathVariable Long id){
        return tripUseCase.getTripById(id);
    }

    private void demoTest() {
        Function<String, User> userCreator = User::new;
    }

    private void demo2() {
        var lista = Arrays.asList(new User("1"));

        lista.forEach(System.out::println);
    }

    private void demo3() {

    }


}
