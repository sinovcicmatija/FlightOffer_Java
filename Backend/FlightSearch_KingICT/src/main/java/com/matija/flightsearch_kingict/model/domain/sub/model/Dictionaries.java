package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

import java.util.Map;

@Data
public class Dictionaries {
    private Map<String, LocationInfo> locations;
    private Map<String, String> aircraft;
    private Map<String, String> carriers;
}
