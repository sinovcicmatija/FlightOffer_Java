package com.matija.flightsearch_kingict.model.external.sub.model;

import lombok.Data;

@Data
public class SearchCriteria {
    private int maxFlightOffers;
    private FlightFilters flightFilters;
}
