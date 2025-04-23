package com.matija.flightsearch_kingict.model.external.sub.model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class SearchCriteria {
    private int maxFlightOffers;
    private FlightFilters flightFilters;
}
