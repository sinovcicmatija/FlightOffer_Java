package com.matija.flightsearch_kingict.model.external.sub.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FlightFilters {
    private List<CabinRestrictions> cabinRestrictions;
}
