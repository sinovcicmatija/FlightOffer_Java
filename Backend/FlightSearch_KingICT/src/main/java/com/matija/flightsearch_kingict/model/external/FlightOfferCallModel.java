package com.matija.flightsearch_kingict.model.external;

import com.matija.flightsearch_kingict.model.external.sub.model.OriginDestinations;
import com.matija.flightsearch_kingict.model.external.sub.model.SearchCriteria;
import com.matija.flightsearch_kingict.model.external.sub.model.Travelers;
import lombok.Data;

@Data
public class FlightOfferCallModel {

    private String currencyCode;
    private OriginDestinations[] originDestinations;
    private Travelers[] travelers;
    private String[] sources;
    private SearchCriteria searchCriteria;
}
