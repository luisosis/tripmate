package com.tripmate.tripmate.api.trip;

import com.tripmate.tripmate.application.trip.TripUseCase;
import com.tripmate.tripmate.domain.User;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/trip")
    public Maybe<TripAuxiliar> getTripByParams(@RequestParam String title){
        return tripUseCase.getTripByParams(title);
    }

    @PostMapping("/save")
    public Completable save(@Valid @RequestBody Trip trip) {
        return tripUseCase.save(trip);
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
