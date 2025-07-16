package com.tripmate.tripmate.domain.mapper;

import com.tripmate.tripmate.domain.trip.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    TripResponse toDto(TripEntity entity);
    TripEntity toEntity(TripRecord trip);

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
