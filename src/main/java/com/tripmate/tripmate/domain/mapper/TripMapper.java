package com.tripmate.tripmate.domain.mapper;

import com.tripmate.tripmate.domain.trip.Exchange;
import com.tripmate.tripmate.domain.trip.Trip;
import com.tripmate.tripmate.domain.trip.TripAuxiliar;
import com.tripmate.tripmate.domain.trip.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    Trip toDto(TripEntity entity);
    TripEntity toEntity(Trip trip);

    @Mapping(source = "trip.title", target = "title")
    @Mapping(source = "trip.description", target = "description")
    @Mapping(source = "trip.destinationCountry", target = "destinationCountry")
    @Mapping(source = "trip.destinationCity", target = "destinationCity")
    @Mapping(source = "trip.startDate", target = "startDate")
    @Mapping(source = "trip.endDate", target = "endDate")
    @Mapping(expression = "java(buildExchange(exchange))", target = "exchange")
    TripAuxiliar toAssistant(Exchange exchange, Trip trip);

    default String buildExchange(Exchange exchange) {
        return exchange.getConversion_rate().toString();
    }
}
