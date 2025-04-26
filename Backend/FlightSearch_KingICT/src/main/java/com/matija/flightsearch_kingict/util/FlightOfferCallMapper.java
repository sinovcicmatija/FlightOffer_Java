package com.matija.flightsearch_kingict.util;

import com.matija.flightsearch_kingict.model.dto.FlightOfferCallDTO;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import com.matija.flightsearch_kingict.model.external.sub.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightOfferCallMapper {
    public static FlightOfferCallModel MapToFlightOfferCallModel(FlightOfferCallDTO dto)
    {
        List<OriginDestinations> originDestinations = new ArrayList<>();

        OriginDestinations outbound = OriginDestinations.builder()
                .id("1")
                .originLocationCode(dto.getOriginIata())
                .destinationLocationCode(dto.getDestinationIata())
                .departureDateTimeRange(
                        DepartureDateTimeRange.builder()
                                .date(dto.getDepartureDate())
                                .build()
                )
                .build();
        originDestinations.add(outbound);

        if(dto.isRoundTrip() && dto.getReturnDate() != null) {
            OriginDestinations returnFlight = OriginDestinations.builder()
                    .id("2")
                    .originLocationCode(dto.getDestinationIata())
                    .destinationLocationCode(dto.getOriginIata())
                    .departureDateTimeRange(
                            DepartureDateTimeRange.builder()
                                    .date(dto.getReturnDate())
                                    .build()
                    )
                    .build();

            originDestinations.add(returnFlight);
        }

        List<Travelers> travelers = new ArrayList<>();

        for(int i = 0; i < dto.getAdults(); i++) {
            travelers.add(Travelers.builder()
                    .id(String.valueOf(i + 1))
                    .travelerType("ADULT")
                    .build());
        }

        for (int i = 0; i < dto.getChildren(); i++) {
            travelers.add(Travelers.builder()
                    .id(String.valueOf(dto.getAdults() + i + 1))
                    .travelerType("CHILD")
                    .build());
        }

        return FlightOfferCallModel.builder()
                .currencyCode(dto.getCurrency())
                .originDestinations(originDestinations)
                .travelers(travelers)
                .sources(List.of("GDS"))
                .searchCriteria(SearchCriteria.builder()
                        .maxFlightOffers(250)
                        .flightFilters(FlightFilters.builder()
                                .cabinRestrictions(List.of(
                                        CabinRestrictions.builder()
                                                .cabin(dto.getCabinClass().toUpperCase())
                                                .coverage("MOST_SEGMENTS")
                                                .originDestinationIds(originDestinations.stream()
                                                        .map(OriginDestinations::getId)
                                                        .collect(Collectors.toList()))
                                                .build()
                                ))
                                .build())
                        .build())
                .build();
    }
}
