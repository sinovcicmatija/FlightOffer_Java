package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class TravelerPricings {
    private String travelerId;
    private String fareOption;
    private String travelerType;
    private Price price;
    private FareDetailsBySegment[] fareDetailsBySegments;
}
