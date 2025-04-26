package com.matija.flightsearch_kingict.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightOfferCallDTO {
    private String originIata;
    private String destinationIata;
    private String departureDate;
    private String returnDate;
    @JsonProperty("isRoundTrip")
    private boolean roundTrip;
    private int adults;
    private int children;
    private String cabinClass;
    private String currency;
}
