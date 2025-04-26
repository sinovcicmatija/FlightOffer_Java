package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class Segments {
    private DepartureArrival departure;
    private DepartureArrival arrival;
    private String carrierCode;
    private String number;
    private Aircraft aircraft;
    private Operating operating;
    private String duration;
    private String id;
    private int numberOfStops;
    private boolean blacklistedInEU;
}
