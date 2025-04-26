package com.matija.flightsearch_kingict.model.domain.sub.model;

@lombok.Data
public class FlightData {
    private String type;
    private String id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    private Itineraries[] itineraries;
    private Price price;
    private PricingOptions pricingOptions;
    private String[] validatingAirlineCodes;
    private TravelerPricings[] travelerPricings;
}
