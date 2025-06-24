package com.tripmate.tripmate.application.trip;

import com.github.benmanes.caffeine.cache.Cache;
import com.tripmate.tripmate.domain.mapper.TripMapper;
import com.tripmate.tripmate.domain.trip.Exchange;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import com.tripmate.tripmate.domain.trip.TripRepository;
import com.tripmate.tripmate.infrastructure.kafka.KafkaProducerService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

@Service
@RequiredArgsConstructor
public class TripUseCaseImpl implements TripUseCase{

    private final TripRepository tripRepository;
    private final WebClient webClient;
    private final TripMapper tripMapper;
    private final Cache<String, TripAuxiliar> tripCache;
    private final KafkaProducerService producerService;

    @Override
    public Observable<Trip> getTrips() {
        return tripRepository.getTrips();
    }


    @Override
    public Maybe<TripAuxiliar> getTripById(Long idTrip) {
        return getUsdToPenExchange()
                .flatMap(exchange -> tripRepository.getTripById(idTrip)
                        .map(trip -> tripMapper.toAssistant(exchange, trip)));
    }

    //@Cacheable(value = "trip_assistant", key = "#title")
    @Override
    public Maybe<TripAuxiliar> getTripByParams(String title) {

        TripAuxiliar cached = tripCache.getIfPresent(title);
        if (cached != null) {
            return Maybe.just(cached);
        }

        //TODO validar cuando no se encuentre en BD
        return getUsdToPenExchange()
                .flatMap(exchange -> tripRepository.getTripByTittle(title)
                        .map(trip -> tripMapper.toAssistant(exchange, trip)))
                .doOnSuccess(tripAuxiliar -> {
                    if (tripAuxiliar != null) {
                        tripCache.put(title, tripAuxiliar);
                    }
                });
    }

    @Override
    public Completable save(Trip trip) {
        return tripRepository.save(trip)
                .andThen(producerService.sendMessageRx("Bienvenido nuevo trip: "
                        + trip.getTitle()));
    }

    private static TripAuxiliar getBuild(Exchange exchange, Trip trip) {
        return TripAuxiliar.builder()
                .title(trip.getTitle())
                .exchange(exchange.getConversion_rate().toString())
                .build();
    }

    private Maybe<Exchange> getUsdToPenExchange() {
        return RxJava3Adapter.monoToMaybe(webClient.get()
                .uri("/fiat/USD/PEN")
                .retrieve()
                .bodyToMono(Exchange.class));
    }
}
