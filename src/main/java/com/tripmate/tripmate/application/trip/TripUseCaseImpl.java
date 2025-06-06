package com.tripmate.tripmate.application.trip;

import com.tripmate.tripmate.domain.trip.Exchange;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import com.tripmate.tripmate.domain.trip.TripRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

@Service
@RequiredArgsConstructor
public class TripUseCaseImpl implements TripUseCase{

    private final TripRepository tripRepository;
    private final WebClient webClient;

    @Override
    public Observable<Trip> getTrips() {
        return tripRepository.getTrips();
    }

    @Override
    public Maybe<TripAuxiliar> getTripById(Long id) {
        return getUsdToPenExchange()
                .flatMap(exchange -> tripRepository.getTripById(id)
                        .map(trip -> TripAuxiliar.builder()
                                .title(trip.getTitle())
                                .exchange(exchange.getConversion_rate().toString())
                                .build()));
    }

    private Maybe<Exchange> getUsdToPenExchange() {
        return RxJava3Adapter.monoToMaybe(webClient.get()
                .uri("/fiat/USD/PEN")
                .retrieve()
                .bodyToMono(Exchange.class));
    }
}
