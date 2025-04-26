package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class LocationData {
    private String subType;
    private String name;
    private String iataCode;
    private Address address;
}
