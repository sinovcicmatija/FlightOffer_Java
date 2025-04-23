package com.matija.flightsearch_kingict.model.dto;

import lombok.Data;

@Data
public class FlightOfferCallDTO {
    private String originIata;
    private String destinationIata;
    private String departureDate;
    private String returnDate;
    private boolean isRoundTrip;
    private int adults;
    private int children;
    private String cabinClass;
    private String currency;
}
