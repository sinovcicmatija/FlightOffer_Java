package com.matija.flightsearch_kingict.model.dto;

import lombok.Data;

@Data
public class AirportDTO {
    private String cityName;
    private String airportName;
    private String iataCode;
    private String countryCode;
}
