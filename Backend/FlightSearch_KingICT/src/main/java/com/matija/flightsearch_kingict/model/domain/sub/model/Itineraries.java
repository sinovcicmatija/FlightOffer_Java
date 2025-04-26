package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class Itineraries {
    private String duration;
    private Segments[] segments;
}
