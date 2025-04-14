package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class Address {
    private String cityName;
    private String cityCode;
    private String countryName;
    private String countryCode;
    private String regionCode;
}
