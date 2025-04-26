package com.matija.flightsearch_kingict.model.external;

import com.matija.flightsearch_kingict.model.external.sub.model.OriginDestinations;
import com.matija.flightsearch_kingict.model.external.sub.model.SearchCriteria;
import com.matija.flightsearch_kingict.model.external.sub.model.Travelers;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FlightOfferCallModel {

    private String currencyCode;
    private List<OriginDestinations> originDestinations;
    private List<Travelers> travelers;
    private List<String> sources;
    private SearchCriteria searchCriteria;
}
