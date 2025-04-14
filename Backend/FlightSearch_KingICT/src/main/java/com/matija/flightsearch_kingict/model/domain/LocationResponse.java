package com.matija.flightsearch_kingict.model.domain;

import com.matija.flightsearch_kingict.model.domain.sub.model.LocationData;
import lombok.Data;

import java.util.List;

@Data
public class LocationResponse {
    private List<LocationData> locationData;
}
