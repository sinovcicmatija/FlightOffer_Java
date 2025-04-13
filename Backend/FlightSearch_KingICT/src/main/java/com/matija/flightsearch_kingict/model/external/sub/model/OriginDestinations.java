package com.matija.flightsearch_kingict.model.external.sub.model;

import lombok.Data;

@Data
public class OriginDestinations {

    private String id;
    private String originLocationCode;
    private String destinationLocationCode;
    private DepartureDateTimeRange departureDateTimeRange;
}
